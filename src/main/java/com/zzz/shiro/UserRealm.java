package com.zzz.shiro;


import com.zzz.model.SysUser;
import com.zzz.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;

    private SysUser user;

    @Override
    //执行授权逻辑
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();



        if(user.getUsername().equals("zzZ")){
            info.addRoles(Permissions.adminPerms);
        }

        if(user.getRole()<=4){
            info.addRoles(Permissions.teamPerms);
        }
        if(user.getRole()<=3){
            info.addRoles(Permissions.groupPerms);
        }
        if(user.getRole()<=2){
            info.addRoles(Permissions.branchPerms);
        }
        if(user.getRole()<=1){
            info.addRoles(Permissions.totalPerms);
        }

        // 添加资源权限
        // info.addStringPermission("structure:query");

        // 添加身份权限
//        info.addRole("structure:query");


        return info;
    }

    @Override
    //执行认证逻辑
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token1) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)token1;

        String currentUsername = token.getUsername();
        user = sysUserService.getSimpleUserByName(currentUsername);

        // 用户不存在
        if(user==null){return null; }

        String password = user.getPassword();
        return new SimpleAuthenticationInfo(user, password, "");
    }
}
