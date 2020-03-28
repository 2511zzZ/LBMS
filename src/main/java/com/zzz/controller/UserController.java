package com.zzz.controller;

import com.zzz.model.SysUser;
import com.zzz.model.SysUserDetails;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    @Autowired
    private SysUserService userService;

    @RequestMapping(value = "/{employeeId}/password", method = RequestMethod.PUT)
    public Results changePassword(int employeeId, String oldPassword, String newPassword){
        if(userService.changePassword(employeeId, oldPassword, newPassword)){
            return Results.success(ResponseCode.SUCCESS,"修改成功");
        }
        return Results.failure(4001, "原密码错误");
    }

    @RequestMapping(value="/{employeeId}/role", method = RequestMethod.GET)
    public Results getRole(int employeeId){
        return Results.success(ResponseCode.SUCCESS, userService.getRole(employeeId));
    }
}
