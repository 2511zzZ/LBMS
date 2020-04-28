package com.zzz.shiro;

import com.zzz.lbms.Utils;
import com.zzz.model.SysUser;
import com.zzz.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;

    private SysUser user;

    @Value("${admin.username}")
    private String adminUsername;
    @Value("${admin.password}")
    private String adminPassword;

    @Override
    //执行授权逻辑
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        if(user.getUsername().equals(adminUsername)){
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
        return info;
    }

    @Override
    //执行认证逻辑
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token1) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)token1;

        // 跳过数据库验证
        if(token.getUsername().equals(adminUsername)){
            user = new SysUser(0, adminUsername, Utils.DoMD5(adminPassword), 1);
            return new SimpleAuthenticationInfo(user, Utils.DoMD5(adminPassword), "");
        }


        String currentUsername = token.getUsername();
        user = sysUserService.getSimpleUserByName(currentUsername);

        // 用户不存在
        if(user==null){return null; }

        String password = user.getPassword();
        return new SimpleAuthenticationInfo(user, password, "");
    }
}
