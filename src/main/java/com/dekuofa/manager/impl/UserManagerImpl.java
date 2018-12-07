package com.dekuofa.manager.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.entity.UserDetail;
import com.dekuofa.model.enums.Gender;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.PasswdParam;
import com.dekuofa.model.relation.UserRole;
import com.dekuofa.service.PermissionService;
import com.dekuofa.service.RoleService;
import com.dekuofa.service.UserDetailService;
import com.dekuofa.service.UserService;
import com.dekuofa.utils.DateUtil;
import com.dekuofa.utils.ShaUtil;
import io.github.biezhi.anima.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

import static com.dekuofa.utils.DateUtil.newUnixMilliSecond;
import static io.github.biezhi.anima.Anima.atomic;

/**
 * @author dekuofa <br>
 * @date 2018-08-22 <br>
 */
@Slf4j
@Component
public class UserManagerImpl implements UserManager {

    private UserService       userService;
    private RoleService       roleService;
    private PermissionService permissionService;
    private UserDetailService userDetailService;

    @Autowired
    public UserManagerImpl(UserService userService, RoleService roleService, PermissionService permissionService, UserDetailService userDetailService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.userDetailService = userDetailService;
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
    public Integer addUser(User user, UserInfo userInfo, String[] roles) throws TipException {
        // todo 校验
        if (userService.isExist(user.getUsername())) {
            throw new TipException("当前用户名已存在");
        }
        // todo 获取角色id
        Long currentTime = newUnixMilliSecond();
        user.setModifyInfo(userInfo, currentTime)
                .setCreateInfo(userInfo, currentTime);

        List<Integer> rolesId = roleService.getRoleIds(roles);

        UserDetail userDetail = new UserDetail();
        userDetail.setModifyInfo(userInfo, currentTime)
                .setCreateInfo(userInfo, currentTime);
        userDetail.setGender(Gender.NULL);
        // 事物开启
        atomic(() -> {
            Integer userId = userService.addUser(user);
            user.setId(userId);
            userDetail.setUserId(userId);
            userDetailService.save(userDetail);
            roleService.addUserRoles(userId, rolesId);
        }).catchException(e -> {
            log.error("新增用户失败：任务回滚");
            e.printStackTrace();
        }).isRollback();
        return user.getId();
    }

    @Override
    public Page<User> queryUser(String username, PageParam pageParam) {
        return userService.query(username, pageParam);
    }

    @Override
    public void updateUserDetail(UserDetail detail, UserInfo userInfo) throws TipException {

        if (!userService.isExist(detail.getUserId())) {
            throw new TipException("当前用户不存在");
        }
        detail.setModifyInfo(userInfo, newUnixMilliSecond());

        userDetailService.modify(detail);
    }

    @Override
    public void changePassword(Integer userId, PasswdParam param, UserInfo userInfo) throws TipException {
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
    public User findById(Integer userId) {
        return userService.getUser(userId);
    }

    @Override
    public UserDetail detail(Integer userId) {
        return userDetailService.findByUserId(userId);
    }

}
