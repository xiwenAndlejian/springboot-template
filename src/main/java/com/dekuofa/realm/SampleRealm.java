package com.dekuofa.realm;


import com.dekuofa.model.entity.User;
import com.dekuofa.model.entity.Role;
import com.dekuofa.service.impl.PermissionServiceImpl;
import com.dekuofa.service.impl.RoleServiceImpl;
import com.dekuofa.service.impl.UserServiceImpl;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@Slf4j
@Component
public class SampleRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl       userService;
    @Autowired
    private RoleServiceImpl       roleService;
    @Autowired
    private PermissionServiceImpl permissionService;


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
        User   user     = userService.getUser(username);
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
        User   user     = userService.getUser(username);

        List<Role>  roles       = getRoleForUser(user);
        Set<String> permissions = getPermissions(roles);
        Set<String> roleNames   = getRolesName(roles);


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    private List<Role> getRoleForUser(User user) {
        return roleService.getRoles(user);
    }

    private Set<String> getPermissions(List<Role> roles) {
        return permissionService.getPermissionsName(roles);
    }

    private Set<String> getRolesName(List<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

}
