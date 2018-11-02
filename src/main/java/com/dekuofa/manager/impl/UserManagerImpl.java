package com.dekuofa.manager.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.PasswdParam;
import com.dekuofa.service.PermissionService;
import com.dekuofa.service.RoleService;
import com.dekuofa.service.UserService;
import com.dekuofa.utils.DateUtil;
import com.dekuofa.utils.ShaUtil;
import io.github.biezhi.anima.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * @author dekuofa <br>
 * @date 2018-08-22 <br>
 */
@Component
public class UserManagerImpl implements UserManager {

    private UserService       userService;
    private RoleService       roleService;
    private PermissionService permissionService;

    @Autowired
    public UserManagerImpl(UserService userService, RoleService roleService, PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        User user = userService.findByUsername(username);
        if (user != null) {
            Collection<SysRole>    roles       = roleService.getRoles(user.getId());
            Collection<Permission> permissions = permissionService.getPermissions(roles);
            user.setSysRoles(roles);
            user.setPermissions(permissions);
        }
        return user;
    }

    @Override
    public void login(Integer userId, String ip) {
        userService.login(userId, ip);
    }

    @Override
    public Integer addUser(User user, UserInfo userInfo) throws TipException {
        // todo 校验
        if (userService.isExist(user.getUsername())) {
            throw new TipException("当前用户名已存在");
        }

        return userService.addUser(user, userInfo);
    }

    @Override
    public Page<User> queryUser(String username, PageParam pageParam) {
        return userService.query(username, pageParam);
    }

    @Override
    public void updateUserInfo(User param, UserInfo userInfo) throws TipException {

        if (!userService.isExist(param.getId())) {
            throw new TipException("更新失败：当前用户不存在");
        }
        param.setModifyInfo(userInfo, DateUtil.newUnixMilliSecond());

        userService.modify(param);
    }

    @Override
    public void changePassword(Integer userId, PasswdParam param, UserInfo userInfo) throws TipException {
        if (!userInfo.isCurrentUser(userId)) {
            throw new TipException("不能修改其他用户密码");
        }
        if (!userService.isExist(userId)) {
            throw new TipException("当前用户不存在");
        }
        User user = userService.getUser(userId);
        // todo 建立一个统一的密码管理器，管理密码生成和校验，而不是选择某一种加密方式
        String encodeOldPasswd = ShaUtil.sha512Encode(param.getOldPasswd());
        if (!user.getPassword().equals(encodeOldPasswd)) {
            throw new TipException("旧密码错误");
        }

        String encodeNewPasswd = ShaUtil.sha512Encode(param.getNewPasswd());

        userService.changePassword(userId, encodeNewPasswd, userInfo);
    }

    @Override
    public void changeAvatar(Integer userId, String avatarPath, UserInfo userInfo) throws TipException {
        if (!userInfo.isCurrentUser(userId)) {
            throw new TipException("不能修改其他用户头像");
        }
        if (!userService.isExist(userId)) {
            throw new TipException("当前用户不存在");
        }

        userService.changeAvatar(userId, avatarPath, userInfo);
    }

    @Override
    public boolean isExist(User user) {
        return userService.isExist(user.getUsername());
    }

    @Override
    public boolean isExist(Integer userId) {
        return userService.isExist(userId);
    }

    @Override
    public void changeStatus(User user) throws TipException {
        if (!isExist(user.getId())) {
            throw new TipException("当前用户不存在");
        }
        User change = new User();
        change.setId(user.getId());
        change.setStatus(user.getStatus());

        userService.modify(change);
    }

    @Override
    public User detail(Integer userId) {
        return userService.getUser(userId);
    }

}
