package com.zzz.controller;

import com.zzz.model.Anchor;
import com.zzz.model.SysUserDetails;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.AnchorService;
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
@RequestMapping("anchor")
public class AnchorController {

    @Autowired
    private AnchorService anchorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Results<Anchor> getAnchorList(@RequestParam(name="page", defaultValue = "1") int page,
                                       @RequestParam(name="pageSize", defaultValue = "30") int pageSize){

//        if(page < 1){
//            return Results.failure(ResponseCode.BAD_REQUEST);
//        }

        List<Anchor> anchorList = anchorService.getAnchors(page, pageSize);
        int total = anchorService.getTotalNum();
        return Results.success(ResponseCode.SUCCESS, total, anchorList);
    }

    @RequestMapping(value="/list/online",method = RequestMethod.GET)
    public Results<Anchor> getOnlineAnchorList(@RequestParam(name="page", defaultValue = "1") int page,
                                               @RequestParam(name="pageSize", defaultValue = "30") int pageSize){
        List<Anchor> onlineAnchorList = anchorService.getOnlineAnchors(page, pageSize);
        int total = anchorService.getOnlineTotalNum();
        return Results.success(ResponseCode.SUCCESS, total, onlineAnchorList);
    }
}
