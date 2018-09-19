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

//        User update = userService.getUser(user.getId());
        if (!userService.isExist(user.getId())) {
            throw new TipException("更新失败：当前用户不存在");
        }
        String password = user.getPassword();
        password = StringUtils.isEmpty(password) ? null : ShaUtil.sha512Encode(password);
        if (StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
//        update.setNickName(user.getNickName());
//        update.setNickName(user.getNickName());
        user.setModifyInfo(userInfo, DateUtil.newUnixMilliSecond());

        userService.modify(user);
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
    public void changeStatus(User user) {
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
