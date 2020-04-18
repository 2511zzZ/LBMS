package com.zzz.controller;

import com.zzz.exception.ForBiddenException;
import com.zzz.exception.NullUsernameException;
import com.zzz.exception.UnAuthorizedException;
import com.zzz.exception.WrongPasswordException;
import com.zzz.result.Results;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Results login(@RequestParam String username,
                         @RequestParam String password) throws NullUsernameException, WrongPasswordException {

        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        System.out.println(password);
        // 执行登录方法
        // 无异常则判断为登录成功
        String sessionId = null;
        try{
            subject.login(token);
            if(subject.getSession()!=null){
                sessionId = (String) subject.getSession().getId();
            }
        }catch (UnknownAccountException e){
            throw new NullUsernameException();
        }catch (IncorrectCredentialsException e){
            throw new WrongPasswordException();
        }
        return Results.success(sessionId);
    }

    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public Results loginFailed() throws UnAuthorizedException {
        throw new UnAuthorizedException();
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public Results unAuthorized() throws ForBiddenException {
        throw new ForBiddenException();
    }
}
