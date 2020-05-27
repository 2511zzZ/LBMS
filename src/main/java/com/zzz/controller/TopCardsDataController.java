package com.zzz.controller;

import com.zzz.exception.BadDateFormatException;
import com.zzz.model.HistoryDatas.AnchorHistoryData;
import com.zzz.model.HistoryDatas.BranchHistoryData;
import com.zzz.model.HistoryDatas.GroupHistoryData;
import com.zzz.model.HistoryDatas.TeamHistoryData;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
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

    @RequestMapping(value = "/online/data", method = RequestMethod.GET)
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


    @RequestMapping(value = "/online/rank", method = RequestMethod.GET)
    public Results getOnlineRank(@RequestParam int levelId, @RequestParam String level){
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

    @RequestMapping(value = "/history/rank", method = RequestMethod.GET)
    public Results getHistoryRank(@RequestParam String dateBeginStr,
                                  @RequestParam String dateEndStr2,
                                  @RequestParam int levelId,
                                  @RequestParam String level) throws BadDateFormatException {

        Date begin,end;
        try{
            begin = new SimpleDateFormat("yyyy-MM-dd").parse(dateBeginStr);
            end = new SimpleDateFormat("yyyy-MM-dd").parse(dateEndStr2);
        }catch (ParseException e){
            throw new BadDateFormatException();
        }

        List<BranchHistoryData> branchHistoryData;
        List<GroupHistoryData> groupHistoryData;
        List<TeamHistoryData> teamHistoryData;
        List<AnchorHistoryData> anchorHistoryData;
        switch (level) {
            case "total":
                branchHistoryData = totalDataService.getBranchHistoryRank(begin,end,levelId);
                return Results.success(ResponseCode.SUCCESS, branchHistoryData.size(), branchHistoryData);
            case "branch":
                groupHistoryData = branchDataService.getGroupHistoryRank(begin,end,levelId);
                return Results.success(ResponseCode.SUCCESS, groupHistoryData.size(), groupHistoryData);
            case "group":
                teamHistoryData = groupDataService.getTeamHistoryRank(begin,end,levelId);
                return Results.success(ResponseCode.SUCCESS, teamHistoryData.size(), teamHistoryData);
            case "team":
                anchorHistoryData = teamDataService.getAnchorHistoryRank(begin,end,levelId);
                return Results.success(ResponseCode.SUCCESS, anchorHistoryData.size(), anchorHistoryData);
            case "anchor":
                anchorHistoryData = teamDataService.getAnchorHistoryRank(begin,end,anchorService.getTeamIdByAnchorId(levelId));
                return Results.success(ResponseCode.SUCCESS, anchorHistoryData.size(), anchorHistoryData);
        }
        return Results.success(ResponseCode.SUCCESS, 0, null);
    }
}
