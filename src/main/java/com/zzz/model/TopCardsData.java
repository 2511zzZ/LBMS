package com.zzz.model;

import lombok.Data;

@Data
public class TopCardsData {

    private Integer onlineAnchorNum;
    private Integer onlineAnchorPercent;
    private Integer yesterdayOnlineAnchorNum;
    private Integer onlineWatcher;
    private Integer giftNum;

    public TopCardsData() {
    }

    public TopCardsData(Integer onlineAnchorNum, Integer onlineAnchorPercent, Integer yesterdayOnlineAnchorNum, Integer onlineWatcher, Integer giftNum) {
        this.onlineAnchorNum = onlineAnchorNum;
        this.onlineAnchorPercent = onlineAnchorPercent;
        this.yesterdayOnlineAnchorNum = yesterdayOnlineAnchorNum;
        this.onlineWatcher = onlineWatcher;
        this.giftNum = giftNum;
    }

}
