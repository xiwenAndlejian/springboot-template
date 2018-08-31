package com.dekuofa.controller;

import com.dekuofa.annotation.SysLog;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.UserInfo;
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
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author dekuofa <br>
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
    public RestResponse<LoginResponse> login(@RequestBody LoginParam param,
                                             @ApiIgnore @ModelAttribute("ip") String ip) {
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
            LoginResponse response = new LoginResponse(userInfo, token, user.getPermissions());
            return RestResponse.ok(response);
        }
        return RestResponse.fail("用户名或密码错误");
    }

    @RequestMapping(value = "/401")
    public RestResponse<?> unAuth() {
        return RestResponse.fail("登陆失败");
    }

}
