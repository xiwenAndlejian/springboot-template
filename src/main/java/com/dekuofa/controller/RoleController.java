package com.dekuofa.controller;

import com.dekuofa.annotation.SysLog;
import com.dekuofa.manager.RoleManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.param.SysRoleParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collection;
import java.util.List;

import static com.dekuofa.utils.CommonValidator.validate;

/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
@RestController
public class RoleController implements BaseController {
    private RoleManager roleManager;

    @Autowired
    public RoleController(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @SysLog(action = "新增角色")
    @RequiresAuthentication
    @PostMapping("/role")
    public RestResponse<?> add(@RequestBody SysRoleParam param,
                               @ApiIgnore UserInfo userInfo) {
        validate(param);

        SysRole role = new SysRole(param);
        if (roleManager.isExist(role)) {
            return RestResponse.fail("新增失败：角色名已存在");
        }
        Long now = DateUtil.newUnixMilliSecond();
        role.setCreateInfo(userInfo, now).setModifyInfo(userInfo, now);

        Integer id = roleManager.addRole(role);
        return RestResponse.ok(id);
    }

    @SysLog(action = "修改角色信息")
    @RequiresAuthentication
    @PutMapping("/role/{id}")
    public RestResponse<?> modify(@PathVariable("id") Integer id,
                                  @RequestBody SysRoleParam param,
                                  @ApiIgnore UserInfo userInfo) {

        if (id == null) {
            RestResponse.fail("修改失败：角色id不能为空");
        }
        SysRole role = new SysRole(param);
        if (roleManager.isExist(role)) {
            return RestResponse.fail("修改失败：角色名已存在");
        }
        role.setModifyInfo(userInfo, DateUtil.newUnixMilliSecond());

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
