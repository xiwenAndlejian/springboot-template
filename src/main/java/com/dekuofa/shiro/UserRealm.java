package com.dekuofa.shiro;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.UserManager;
import com.dekuofa.model.BaseUserInfo;
import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.model.entity.User;
import com.dekuofa.utils.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ganxiang <br>
 * 时间：2018年04月25日 14:32<br>
 * 标题：MyShiroRealm<br>
 * 功能：权限校验<br>
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private UserManager       userManager;
    // 解决shiro加载顺序导致的@Cacheable无效的情况

    @Lazy
    @Autowired
    public UserRealm(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        final String token = principals.toString();
        BaseUserInfo userInfo = JwtUtil.getUserInfo(token)
                .orElseThrow(() -> new TipException("token异常"));

        if (userInfo.isEmpty()) {
            throw new TipException("token异常");
        }
        User            user        = getUser(userInfo);
        Collection<SysRole>    sysRoles    = user.getSysRoles();
        Collection<Permission> permissions = user.getPermissions();

        Set<String>             roleNames          = getRolesName(sysRoles);
        Set<String>             permissionNames    = getPermissionName(permissions);
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        authenticationInfo.addRoles(roleNames);
        authenticationInfo.addStringPermissions(permissionNames);
        return authenticationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
        final String token = (String) auth.getCredentials();
        BaseUserInfo userInfo = JwtUtil.getUserInfo(token)
                .orElseThrow(() -> new UnknownAccountException("无效的用户信息"));
        if (userInfo.isEmpty()) {
            throw new UnknownAccountException("无效的用户信息");
        }
        // 如果user找到但锁定了抛出锁定异常LockedAccountException
        User user = getUser(userInfo);
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        if (!JwtUtil.verify(token, userInfo, user.getPassword())) {
            throw new AuthenticationException("token校验异常");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

    private User getUser(BaseUserInfo userInfo) {
        User user = userManager.findByUsername(userInfo.getUsername());
        return user;
    }

    private Set<String> getRolesName(Collection<SysRole> sysRoles) {
        return sysRoles.stream().map(SysRole::getName).collect(Collectors.toSet());
    }

    private Set<String> getPermissionName(Collection<Permission> permissions) {
        return permissions.stream().map(Permission::getName).collect(Collectors.toSet());
    }


}
