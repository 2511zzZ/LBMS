package com.zzz.controller;

import com.zzz.model.OnlineDatas.AnchorOnlineData;
import com.zzz.model.OnlineDatas.BranchOnlineData;
import com.zzz.model.OnlineDatas.GroupOnlineData;
import com.zzz.model.OnlineDatas.TeamOnlineData;
import com.zzz.model.SysUser;
import com.zzz.model.TopCardsData;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private TotalDataService totalDataService;
    @Autowired
    private BranchDataService branchDataService;
    @Autowired
    private GroupDataService groupDataService;
    @Autowired
    private TeamDataService teamDataService;

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


    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public Results getGroupOnlineRank(@RequestParam int levelId, @RequestParam String level){
        List<BranchOnlineData> branchOnlineData;
        List<GroupOnlineData> groupOnlineData;
        List<TeamOnlineData> teamOnlineData;
        List<AnchorOnlineData> anchorOnlineData;
        switch (level) {
            case "total":
                branchOnlineData = totalDataService.getBranchOnlineRank(levelId);
                return Results.success(ResponseCode.SUCCESS, branchOnlineData.size(), branchOnlineData);
            case "branch":
                groupOnlineData = branchDataService.getGroupOnlineRank(levelId);
                return Results.success(ResponseCode.SUCCESS, groupOnlineData.size(), groupOnlineData);
            case "group":
                teamOnlineData = groupDataService.getTeamOnlineRank(levelId);
                return Results.success(ResponseCode.SUCCESS, teamOnlineData.size(), teamOnlineData);
            case "team":
                anchorOnlineData = teamDataService.getAnchorOnlineRank(levelId);
                return Results.success(ResponseCode.SUCCESS, anchorOnlineData.size(), anchorOnlineData);
            case "anchor":
                anchorOnlineData = teamDataService.getAnchorOnlineRank(anchorService.getTeamIdByAnchorId(levelId));
                return Results.success(ResponseCode.SUCCESS, anchorOnlineData.size(), anchorOnlineData);
        }
        return Results.success(ResponseCode.SUCCESS, 0, null);
    }
}
