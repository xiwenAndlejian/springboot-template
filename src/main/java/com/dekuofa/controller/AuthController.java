package com.dekuofa.controller;

import com.dekuofa.annotation.SysLog;
import com.dekuofa.manager.RoleManager;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.enums.UserType;
import com.dekuofa.model.param.LoginParam;
import com.dekuofa.model.response.LoginResponse;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.model.response.UserInfoResponse;
import com.dekuofa.utils.JwtUtil;
import com.dekuofa.utils.ShaUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author dekuofa <br>
 * @date 2018-08-14 <br>
 */
@RestController
@Slf4j
public class AuthController implements BaseController {
    private UserManager userManager;
    private RoleManager roleManager;

    @Autowired
    public AuthController(UserManager userManager, RoleManager roleManager) {
        this.userManager = userManager;
        this.roleManager = roleManager;
    }

    @SysLog(action = "登陆")
    @PostMapping("/login")
    public RestResponse<?> login(@RequestBody LoginParam param,
                                 @ApiIgnore @ModelAttribute("ip") String ip,
                                 @ApiIgnore HttpServletResponse response) {
        if (param == null || param.getUsername() == null) {
            throw new UnauthorizedException("用户名或密码不能为空");
        }
        log.debug("用户登陆ip：" + ip);
        User user = userManager.findByUsername(param.getUsername());
        // 加密后的密码
        String encodePwd = ShaUtil.sha512Encode(param.getPassword());
        if (user != null && user.getPassword().equals(encodePwd)) {
            UserInfo userInfo =
                    new UserInfo(user.getId(), user.getUsername(), user.getNickName(), UserType.ADMIN);
            // 生成token
            String token =
                    JwtUtil.sign(userInfo, encodePwd);
            if (token == null) {
                return RestResponse.fail("生成token失败");
            }
            // 记录登陆成功，修改最后一次登录时间
            userManager.login(user.getId(), ip);
            // 组装返回数据
            LoginResponse responseData = new LoginResponse(userInfo, token, user.getPermissions());
            // cookie设置
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return RestResponse.ok(responseData);
        }
        return RestResponse.fail("用户名或密码错误");
    }

    @PostMapping("/user/logout")
    public RestResponse<?> logout(@ApiParam(hidden = true) UserInfo userInfo) {
        log.info("用户: {} 已下线", userInfo.getNickName());
        return RestResponse.ok();
    }

    @RequiresAuthentication
    @GetMapping("/user/info")
    public RestResponse<?> getUserInfo(@ApiParam(hidden = true) UserInfo userInfo) {
        if (userInfo == null) {
            return RestResponse.fail("token校验失败");
        }
        Integer userId = userInfo.getUserId();
        User    user   = userManager.detail(userId);
        Collection<String> roles = roleManager.roles(userId)
                .stream().map(SysRole::getName)
                .collect(Collectors.toList());
        UserInfoResponse response =
                new UserInfoResponse().name(userInfo.getNickName()).roles(roles).id(userId)
                        .avatar(user.getAvatar());
        return RestResponse.ok(response);
    }

    @RequestMapping(value = "/401")
    public RestResponse<?> unAuth() {
        return RestResponse.fail("登陆失败");
    }

}
