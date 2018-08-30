package com.dekuofa.controller;

import com.dekuofa.manager.RoleManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.param.SysRoleParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.dekuofa.utils.CommonValidator.validate;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
@RestController
public class RoleController {
    private RoleManager roleManager;

    @Autowired
    public RoleController(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @RequiresAuthentication
    @PostMapping("/role")
    public RestResponse<?> add(@RequestBody SysRoleParam param, UserInfo userInfo) {
        validate(param);

        SysRole role = new SysRole(param);
        if (roleManager.isExist(role)) {
            return RestResponse.fail("新增失败：角色名已存在");
        }
        int now = DateUtil.newUnix();
        role.setCreatorId(userInfo.getUserId());
        role.setCreatorName(userInfo.getNickName());
        role.setCreateTime(now);

        role.setModifierId(userInfo.getUserId());
        role.setModifierName(userInfo.getNickName());
        role.setModifyTime(now);

        Integer id = roleManager.addRole(role);
        return RestResponse.ok(id);
    }

    @RequiresAuthentication
    @PutMapping("/role/{id}")
    public RestResponse<?> modify(@PathVariable("id") Integer id,
                                  @RequestBody SysRoleParam param,
                                  UserInfo userInfo) {

        if (id == null) {
            RestResponse.fail("修改失败：角色id不能为空");
        }
        SysRole role = new SysRole(param);
        if (roleManager.isExist(role)) {
            return RestResponse.fail("修改失败：角色名已存在");
        }
        int now = DateUtil.newUnix();
        role.setModifierId(userInfo.getUserId());
        role.setModifierName(userInfo.getNickName());
        role.setModifyTime(now);

        role.setId(id);
        roleManager.modify(role);
        return RestResponse.ok(id);
    }

    @GetMapping("/role")
    public RestResponse<?> list() {
        Collection<SysRole> roles = roleManager.list();
        return RestResponse.ok().payload(roles);
    }
}
