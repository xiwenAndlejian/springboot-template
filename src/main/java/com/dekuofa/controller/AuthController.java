package com.dekuofa.controller;

import com.dekuofa.annotation.SysLog;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.enums.UserType;
import com.dekuofa.model.param.LoginParam;
import com.dekuofa.model.response.LoginResponse;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.JwtUtil;
import com.dekuofa.utils.ShaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@RestController
@Slf4j
public class AuthController {
    private UserManager userManager;

    @Autowired
    public AuthController(UserManager userManager) {
        this.userManager = userManager;
    }

    @SysLog(action = "登陆")
    @PostMapping("/login")
    public RestResponse<LoginResponse> login(@RequestBody LoginParam param, @ModelAttribute("ip") String ip) throws Exception {
        if (param == null || param.getUsername() == null) {
            throw new UnauthorizedException("用户名或密码不能为空");
        }
        log.info("用户登陆ip：" + ip);
        User user = userManager.findByUsername(param.getUsername());
        // 加密后的密码
        String encodePwd = ShaUtil.sha512Encode(param.getPassword());
        if (user != null && user.getPassword().equals(encodePwd)) {
            // 生成token
            String token =
                    JwtUtil.sign(user.getId(), user.getUsername(), encodePwd, user.getNickName(), UserType.ADMIN);
            if (token == null) {
                return RestResponse.fail("生成token失败");
            }
            // 记录登陆成功，修改最后一次登录时间
            userManager.login(user.getId(), ip);

            // 组装返回数据
            LoginResponse response = new LoginResponse(token, user.getPermissions());
            return RestResponse.ok(response);
        }
        throw new UnauthorizedException("用户名或密码错误");
    }

    @RequestMapping(value = "/401")
    public RestResponse<?> unAuth() {
        return RestResponse.fail("登陆失败");
    }

}
