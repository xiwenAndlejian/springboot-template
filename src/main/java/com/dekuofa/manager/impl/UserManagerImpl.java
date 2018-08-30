package com.dekuofa.manager.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.BaseUserInfo;
import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.param.PageParam;
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
    public Integer addUser(User user, BaseUserInfo userInfo) {
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
    public void updateUser(User user, BaseUserInfo userInfo) {



        User update = userService.getUser(user.getId());
        if (update == null) {
            throw new TipException("更新失败：当前用户不存在");
        }
        update.setPassword(StringUtils.isEmpty(user.getPassword()) ? null : ShaUtil.sha512Encode(user.getPassword()));
        update.setNickName(user.getNickName());
        update.setModifyTime(DateUtil.newUnix());
        update.setModifierId(userInfo.getUserId());
        update.setModifierName(userInfo.getNickName());

        userService.modify(update);
    }

    @Override
    public boolean isExist(User user) {
        return userService.isExist(user.getUsername());
    }

    @Override
    public boolean isExist(Integer userId) {
        return userService.isExist(userId);
    }

}
