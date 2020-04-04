package com.zzz.controller;

import com.zzz.model.SysUser;
import com.zzz.model.SysUserDetails;
import com.zzz.model.SysUserSettings;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    @Autowired
    private SysUserService userService;

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public Results changePassword(@RequestParam String oldPassword,
                                  @RequestParam String newPassword){

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int employeeId = user.getEmployeeId();

        if(userService.changePassword(employeeId, oldPassword, newPassword)){
            return Results.success(ResponseCode.SUCCESS,"修改成功");
        }
        return Results.failure(4001, "原密码错误");
    }

    @RequestMapping(value="/role", method = RequestMethod.GET)
    public Results getRole(int employeeId){
        return Results.success(ResponseCode.SUCCESS, userService.getRole(employeeId));
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public Results<SysUserSettings> getSettings(){
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int employeeId = user.getEmployeeId();
        return Results.success(ResponseCode.SUCCESS, userService.getSettings(employeeId));
    }
    @RequestMapping(value = "/settings", method = RequestMethod.PUT)
    public Results<SysUserSettings> changeSettings(@RequestParam String setting1,
                                                   @RequestParam String setting2){

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int employeeId = user.getEmployeeId();

        userService.changeSettings(employeeId,setting1, setting2);
        return Results.success(ResponseCode.SUCCESS);
    }
}
