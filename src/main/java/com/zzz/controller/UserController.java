package com.zzz.controller;

import com.zzz.model.SysUser;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    @Autowired
    private SysUserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Results<SysUser> userList(@RequestParam(name="page", defaultValue = "1") int page,
                                     @RequestParam(name="pageSize", defaultValue = "30") int pageSize){

//        if(page < 1){
//            return Results.failure(ResponseCode.BAD_REQUEST);
//        }

        List<SysUser> userArrayList = userService.getUsers(page, pageSize);
        int total = userService.getTotalNum();
        return Results.success(ResponseCode.SUCCESS, total, userArrayList);
    }
}
