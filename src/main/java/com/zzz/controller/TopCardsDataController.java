package com.zzz.controller;

import com.zzz.model.SysUser;
import com.zzz.model.TopCardsData;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.AnchorService;
import com.zzz.service.LevelService;
import com.zzz.service.TopCardsDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/online")
public class TopCardsDataController {

    @Autowired
    private AnchorService anchorService;
    @Autowired
    private LevelService levelService;
    @Autowired
    private TopCardsDataService topCardsDataService;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Results<TopCardsData> getLastOnlineData(){
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        Integer onlineNum = anchorService.getOnlineAnchorNum(user);

        Integer totalNum = levelService.countAnchor(user.getRole(), user.getEmployeeId());
        Integer percent = onlineNum * 100 / totalNum;

        Integer yesterdayOnlineAnchorNum = new Double(onlineNum * 1.2).intValue();

        Integer onlineWatcher = topCardsDataService.getOnlineWatcher(user);

        Integer giftSum = topCardsDataService.getGiftSum(user);

        return Results.success(ResponseCode.SUCCESS, new TopCardsData(onlineNum, percent, yesterdayOnlineAnchorNum, onlineWatcher, giftSum));
    }
}
