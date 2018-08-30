package com.dekuofa.controller;

import com.dekuofa.manager.PermissionManager;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
@RestController
public class PermissionController {

    private PermissionManager permissionManager;

    @Autowired
    public PermissionController(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }

    @GetMapping("/permission")
    public RestResponse<?> list(PageParam pageParam) {
        return RestResponse.ok(permissionManager.list(pageParam));
    }

}
