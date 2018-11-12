package com.dekuofa.controller;

import com.dekuofa.annotation.SysLog;
import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.manager.RoleManager;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.entity.UserDetail;
import com.dekuofa.model.enums.BaseStatus;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.PasswdParam;
import com.dekuofa.model.param.UserDetailParam;
import com.dekuofa.model.param.UserParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.CommonValidator;
import com.dekuofa.utils.ShaUtil;
import io.github.biezhi.anima.page.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

import static com.dekuofa.utils.CommonKit.difference;

/**
 * @author dekuofa <br>
 * @date 2018-08-22 <br>
 */
@RestController
public class UserController implements BaseController {

    private UserManager userManager;
    private RoleManager roleManager;

    @Autowired
    public UserController(UserManager userManager,
                          RoleManager roleManager) {
        this.userManager = userManager;
        this.roleManager = roleManager;
    }

    @SysLog(action = "新增用户")
    @RequiresAuthentication
    @PostMapping("/user")
    public RestResponse<Integer> saveUser(@RequestBody @Valid UserParam userParam,
                                          @ApiIgnore UserInfo userInfo) {
        User user = new User(userParam);
        try {
            // 加密
            String password = ShaUtil.sha512Encode(user.getPassword());
            user.setPassword(password);
            user.setStatus(BaseStatus.INTI);

            if (StringUtils.isEmpty(user.getAvatar())) {
                user.setAvatar(Constants.DEFAULT_USER_AVATAR);
            }
            int id = userManager.addUser(user, userInfo);
            return RestResponse.ok(id);
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg);
        }
    }

    @RequiresAuthentication
    @GetMapping("/user/detail")
    public RestResponse<?> userDetail(@ModelAttribute UserInfo userInfo) {
        try {
            UserDetail detail = userManager.detail(userInfo.getUserId());
            return RestResponse.ok(detail);
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg);
        }
    }

    @SysLog(action = "修改用户详情")
    @RequiresAuthentication
    @PutMapping("/user/detail")
    public RestResponse<?> updateUser(@Valid @RequestBody UserDetailParam param,
                                      @ModelAttribute UserInfo userInfo) {

        UserDetail detail = UserDetail.build(param);
        detail.setUserId(userInfo.getUserId());
        try {
            userManager.updateUserDetail(detail, userInfo);
            return RestResponse.ok();
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg);
        }
    }

    @SysLog(action = "修改密码")
    @PutMapping("/user/{id}/passwd")
    public RestResponse<?> changePasswd(@PathVariable("id") Integer id,
                                        @RequestBody PasswdParam param,
                                        UserInfo userInfo) {
        if (!userInfo.isCurrentUser(id)) {
            return RestResponse.fail("不能修改其他用户的密码");
        }
        CommonValidator.validate(param);
        try {
            userManager.changePassword(id, param, userInfo);
            return RestResponse.ok();
        } catch (TipException e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg).code(e.getCode());
        }
    }

    @SysLog(action = "修改头像")
    @PutMapping("/user/{id}/avatar")
    public RestResponse<?> changeAvatar(@PathVariable("id") Integer id,
                                        @RequestParam String avatarPath,
                                        UserInfo userInfo) {
        if (!userInfo.isCurrentUser(id)) {
            return RestResponse.fail("不能修改其他用户的头像");
        }
        if (StringUtils.isEmpty(avatarPath)) {
            return RestResponse.fail("头像文件路径不能为空");
        }
        // todo 校验 avatarPath 格式是否满足路径
        try {
            userManager.changeAvatar(id, avatarPath, userInfo);
            return RestResponse.ok();
        } catch (TipException e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg).code(e.getCode());
        }
    }

    @SysLog(action = "修改用户状态")
    @PutMapping("/user/{id}/status")
    public RestResponse<?> changeStatus(@PathVariable("id") Integer id,
                                        @RequestParam BaseStatus status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);

        try {
            userManager.changeStatus(user);
            return RestResponse.ok();
        } catch (Exception e) {
            String msg = Constants.ERROR_MESSAGE;
            if (e instanceof TipException) {
                msg = e.getMessage();
            }
            e.printStackTrace();
            return RestResponse.fail(msg);
        }
    }

    @GetMapping("/user")
    public RestResponse<Page<User>> query(String username, PageParam pageParam) {
        return RestResponse
                .ok(userManager.queryUser(username, pageParam));
    }

    @GetMapping("/user/{id}")
    public RestResponse<?> detail(@PathVariable Integer id) {
        User user = userManager.findById(id);
        return RestResponse.ok(user);
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

    @SysLog(action = "修改用户角色")
    @PutMapping("/user/{id}/role")
    public RestResponse<?> changeUserRoles(@PathVariable("id") Integer userId,
                                           @RequestParam("roleIds") Integer[] roleIds,
                                           @ApiIgnore UserInfo userInfo) {
        if (userId == null || !userManager.isExist(userId)) {
            return RestResponse.fail("修改失败：用户不存在");
        }
        Integer[]    userRoles   = roleManager.roleIds(userId).toArray(new Integer[]{});
        Set<Integer> addRoles    = difference(roleIds, userRoles);
        Set<Integer> deleteRoles = difference(userRoles, roleIds);
        try {
            roleManager.changeUserRoles(userId, addRoles, deleteRoles);
        } catch (Exception e) {
            String msg = getErrorMessage(e);
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
