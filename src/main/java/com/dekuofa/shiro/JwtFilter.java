package com.dekuofa.shiro;

import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ganxiang <br>
 * 时间：2018年05月03日 18:46<br>
 * 标题：JWTFilter<br>
 * 功能：json web token 过滤器<br>
 */
@Log4j2
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest  httpServletRequest  = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     *
     * 做出修改，只有校验登录状态通过的才返回true
     * 其余均返回false，且显示异常信息
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                // 校验登录状态
                executeLogin(request, response);
                return true;
            } catch (AuthenticationException e) {
                // 身份校验失败
                log.error("校验token失败：");
                e.printStackTrace();
                responseError(request, response, e);
            }
        } else {
            // 请求头中不包含 token
            response401(request, response);
        }
        return false;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req           = (HttpServletRequest) request;
        String             authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws AuthenticationException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String             authorization      = JwtUtil.getToken(httpServletRequest.getHeader("Authorization"));
        JwtToken           token              = new JwtToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 返回错误代码401
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        RestResponse restResponse = RestResponse.fail("校权失败，请登录后重试").code(200);
        writeJsonMessageToResp(resp, restResponse);
    }

    /**
     * 返回校验异常
     */
    private void responseError(ServletRequest req, ServletResponse resp, Exception error) {
        RestResponse restResponse = RestResponse.fail(error.getMessage()).code(200);
        writeJsonMessageToResp(resp, restResponse);
    }

    private void writeJsonMessageToResp(ServletResponse resp, RestResponse restResponse) {
        try {
            OutputStream out     = resp.getOutputStream();
            byte[]       message = objectMapper.writeValueAsBytes(restResponse);
            out.write(message);
            resp.setContentType("application/json");
        } catch (IOException e) {
            log.error("信息写入失败：{}", e.getCause());
            e.printStackTrace();
        }
    }
}
