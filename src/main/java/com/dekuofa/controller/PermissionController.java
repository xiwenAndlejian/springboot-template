package com.dekuofa.controller;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.PermissionManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.response.RestResponse;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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

    @RequiresAuthentication
    @PutMapping("/role/{id}/permission")
    public RestResponse<?> changePermission(@ApiIgnore UserInfo userInfo,
                                            @PathVariable("id") Integer id,
                                            @RequestParam("permissionIds") List<Integer> permissionIds) {
        try {
            permissionManager.changePermissions(id, permissionIds);
            return RestResponse.ok();
        }  catch (Exception e) {

            String msg;
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                msg = "修改权限失败：服务异常";
            }
            return RestResponse.fail(msg);
        }
    }

}
