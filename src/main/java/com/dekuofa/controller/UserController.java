package com.dekuofa.controller;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.UserParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.service.UserService;
import io.github.biezhi.anima.page.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author gx <br>
 * @date 2018-08-22 <br>
 */
@RestController
public class UserController {

    private UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/user")
    public RestResponse<Integer> saveUser(@RequestBody @Valid UserParam userParam,
                                          UserInfo userInfo) {
        User user = new User(userParam);
        try {
            int id = userManager.addUser(user, userInfo);
            return RestResponse.ok(id);
        } catch (Exception e) {
            if (e instanceof TipException) {
                return RestResponse.fail(e.getMessage());
            }
            return RestResponse.fail("新增用户失败:" + e.getMessage());
        }
    }

    @RequiresAuthentication
    @PutMapping("/user/{id}")
    public RestResponse<?> updateUser(@PathVariable("id") int userId,
                                      @RequestBody User user,
                                      @ModelAttribute UserInfo userInfo) {
        if (!userInfo.isCurrentUser(userId)) {
            return RestResponse.fail("无法修改其他用户信息");
        }
        user.setId(userId);
        userManager.updateUser(user, userInfo);
        return RestResponse.ok();
    }

    @GetMapping("/user")
    public RestResponse<Page<User>> query(String username, PageParam pageParam) {
        return RestResponse
                .ok(userManager.queryUser(username, pageParam));
    }
}
