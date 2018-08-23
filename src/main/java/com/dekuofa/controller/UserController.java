package com.dekuofa.controller;

import com.dekuofa.manager.UserManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.UserParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.service.UserService;
import io.github.biezhi.anima.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public RestResponse<Integer> saveUser(UserParam userParam, UserInfo userInfo) {
        return null;
    }

    @GetMapping("/users")
    public RestResponse<Page<User>> query(String username, PageParam pageParam) {
        return RestResponse
                .ok(userManager.queryUser(username, pageParam));
    }
}
