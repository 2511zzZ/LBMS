package com.zzz.controller;

import com.zzz.exception.ForBiddenException;
import com.zzz.model.Anchor;
import com.zzz.model.SysUser;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.AnchorService;
import com.zzz.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/anchor")
public class AnchorController {

    @Autowired
    private AnchorService anchorService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value="/list/online",method = RequestMethod.GET)
    public Results<Anchor> getOnlineAnchorList(@RequestParam(name="page", defaultValue = "1") int page,
                                               @RequestParam(name="pageSize", defaultValue = "30") int pageSize){
        List<Anchor> onlineAnchorList = anchorService.getOnlineAnchors(page, pageSize);
        int total = anchorService.getOnlineTotalNum();
        return Results.success(ResponseCode.SUCCESS, total, onlineAnchorList);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Results<Anchor> getAnchor(@RequestParam int anchorId) throws ForBiddenException {

        // 判断anchorId是否在权限范围内
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        if(!permissionService.hasPermission(user,anchorId)){
            throw new ForBiddenException();
        }


        return Results.success(ResponseCode.SUCCESS, anchorService.getAnchor(anchorId));
    }


    @RequestMapping(value="/ban",method = RequestMethod.POST)
    public Results banAnchor(@RequestParam int anchorId,
                             @RequestParam String startStr,
                             @RequestParam String endStr,
                             @RequestParam String reason){
        Date begin;
        Date end;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            begin = simpleDateFormat.parse(startStr);
            end = simpleDateFormat.parse(endStr);
        }catch (ParseException e){
            return Results.failure(4002, "日期格式错误");
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        if(!permissionService.hasPermission(user,anchorId)){
            return Results.failure(403, "未授权");
        }

        anchorService.banAnchor(anchorId, begin, end, reason);
        return Results.success(ResponseCode.SUCCESS);
    }

    @RequestMapping(value="/recommend", method = RequestMethod.GET)
    public Results<Anchor> anchorRecommend(){
        List<Anchor> anchors = anchorService.recommendAnchors();
        return Results.success(ResponseCode.SUCCESS, anchors.size(), anchors);
    }
}
