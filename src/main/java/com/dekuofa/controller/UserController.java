package com.dekuofa.controller;

import com.dekuofa.model.UserInfo;
import com.dekuofa.model.param.UserParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gx <br>
 * @date 2018-08-22 <br>
 */
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public RestResponse<Integer> saveUser(UserParam userParam, UserInfo userInfo) {
        return null;
    }
}
