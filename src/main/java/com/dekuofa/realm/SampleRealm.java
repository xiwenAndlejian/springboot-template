package com.dekuofa.realm;


import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.User;
import com.dekuofa.model.entity.SysRole;
import com.dekuofa.service.PermissionService;
import com.dekuofa.service.RoleService;
import com.dekuofa.service.UserService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@Slf4j
@Component
@NoArgsConstructor
public class SampleRealm extends AuthorizingRealm {

    private UserService       userService;
    private RoleService       roleService;
    private PermissionService permissionService;

    @Autowired
    public SampleRealm(UserService userService,
                       RoleService roleService,
                       PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @Setter
    private String username;

    @Override
    public String getName() {
        return username;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return true;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken  = (UsernamePasswordToken) token;
        String                username = upToken.getUsername();


        // 用户名不能为空
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        // 根据用户名获取用户信息
        User   user     = userService.findByUsername(username);
        String password = user.getPassword();
        // todo 加盐加密
//        String salt     = null;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password.toCharArray(), getName());

//        if (salt != null) {
//            info.setCredentialsSalt(ByteSource.Util.bytes(salt));
//        }


        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principals);
        User   user     = userService.findByUsername(username);

        Set<SysRole>    sysRoles    = roleService.getRoles(user.getId());
        Set<Permission> permissions = permissionService.getPermissions(sysRoles);

        Set<String> roleNames       = getRolesName(sysRoles);
        Set<String> permissionNames = getPermissionName(permissions);


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissionNames);
        return info;
    }

    private Set<String> getRolesName(Collection<SysRole> sysRoles) {
        return sysRoles.stream().map(SysRole::getName).collect(Collectors.toSet());
    }

    private Set<String> getPermissionName(Collection<Permission> permissions) {
        return permissions.stream().map(Permission::getName).collect(Collectors.toSet());
    }

}
