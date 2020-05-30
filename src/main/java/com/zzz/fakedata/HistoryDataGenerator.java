package com.zzz.fakedata;

import com.zzz.model.HistoryDatas.AnchorHistoryData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDataGenerator {
    public static List<AnchorHistoryData> fakeAnchorHistoryData(List<Integer> anchorIds, Date datetime) {
        List<AnchorHistoryData> anchorHistoryDataList = new ArrayList<>();
        // int anchorId, int watchNum, int gift, int bulletScreen, int maxWatchNum, Date date
        for(Integer anchorId: anchorIds){
            anchorHistoryDataList.add(new AnchorHistoryData(
                    anchorId,
                    FakeDataUtils.randomWatchNum(),
                    FakeDataUtils.randomHistoryGift(),
                    FakeDataUtils.randomBulletScreen(),
                    FakeDataUtils.randomMaxWatchNum(),
                    datetime
            ));
        }
        return anchorHistoryDataList;
    }
}

