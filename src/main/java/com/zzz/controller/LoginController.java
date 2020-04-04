package com.zzz.controller;

import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Results login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 执行登录方法
        // 无异常则判断为登录成功
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            return Results.failure(4001,"用户名不存在");
        }catch (IncorrectCredentialsException e){
            return Results.failure(4002, "密码错误");
        }
        return Results.success();
    }

    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public Results loginFailed(){
        return Results.failure(ResponseCode.UNAUTHORIZED);
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public Results unAuthorized(){
        return Results.failure(ResponseCode.FORBIDDEN);
    }
}
