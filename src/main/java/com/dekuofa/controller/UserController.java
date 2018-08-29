package com.dekuofa.controller;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.RoleManager;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.UserParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.DateUtil;
import com.dekuofa.utils.ShaUtil;
import io.github.biezhi.anima.page.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

import static com.dekuofa.utils.CommonKit.difference;

/**
 * @author gx <br>
 * @date 2018-08-22 <br>
 */
@RestController
public class UserController {

    private UserManager userManager;
    private RoleManager roleManager;

    @Autowired
    public UserController(UserManager userManager,
                          RoleManager roleManager) {
        this.userManager = userManager;
        this.roleManager = roleManager;
    }

    @PostMapping("/user")
    public RestResponse<Integer> saveUser(@RequestBody @Valid UserParam userParam,
                                          UserInfo userInfo) {
        User user = new User(userParam);
        try {
            // 加密
            String password = ShaUtil.sha512Encode(user.getPassword());
            user.setPassword(password);
            int now = DateUtil.newUnix();
            user.setCreateTime(now);
            user.setModifyTime(now);
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
    public RestResponse<?> updateUser(@PathVariable("id") Integer userId,
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

    @GetMapping("/user/{id}/role")
    public RestResponse<?> userRoles(@PathVariable("id") Integer userId) {
        // todo 应该修改为只能当前用户和admin角色查询
        if (userId == null) {
            RestResponse.fail("用户id不能为空");
        }
        Collection<SysRole> roles = roleManager.roles(userId);
        return RestResponse.ok(roles);
    }

    @PutMapping("/user/{id}/role")
    public RestResponse<?> changeUserRoles(@PathVariable("id") Integer userId,
                                           @RequestParam("roleIds") Integer[] roleIds,
                                           UserInfo userInfo) {
        if (userId == null || !userManager.isExist(userId)) {
            return RestResponse.fail("修改失败：用户不存在");
        }
        Integer[]    userRoles   = roleManager.roleIds(userId).toArray(new Integer[]{});
        Set<Integer> addRoles    = difference(roleIds, userRoles);
        Set<Integer> deleteRoles = difference(userRoles, roleIds);
        try {
            roleManager.changeUserRoles(userId, addRoles, deleteRoles);
        } catch (Exception e) {
            String msg;
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                msg = "服务异常：修改角色失败";
            }
            return RestResponse.fail(msg);
        }
        return RestResponse.ok();
    }

    @PostMapping("/unique/user/username")
    public RestResponse<?> checkExist(@RequestParam("username") String username) {
        if (StringUtils.isEmpty(username)) {
            return RestResponse.fail("用户名不能为空");
        }
        User user = new User();
        user.setUsername(username);
        boolean isExist = userManager.isExist(user);
        if (isExist) {
            return RestResponse.fail("用户名已存在").payload(username);
        }
        return RestResponse.ok();
    }
}
