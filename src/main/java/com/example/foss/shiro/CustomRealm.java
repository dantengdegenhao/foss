package com.example.foss.shiro;


import com.example.foss.pojo.User;
import com.example.foss.service.RoleService;
import com.example.foss.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * 描述：
 *
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private UserService userServer;
    @Autowired
    @Lazy
    private RoleService roleService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(roleService.getById(user.getRoleId()).getRole());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken Token = (UsernamePasswordToken) authenticationToken;
        User byMobile = userServer.getByMobile(Token.getUsername());
        if (byMobile==null){
            return null;
        }
        return new SimpleAuthenticationInfo(byMobile,byMobile.getPassword(),
                ByteSource.Util.bytes(byMobile.getSalt()),getName());
    }
}

