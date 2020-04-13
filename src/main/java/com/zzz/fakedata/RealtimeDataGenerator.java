package com.zzz.fakedata;

import com.zzz.model.OnlineDatas.AnchorOnlineData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RealtimeDataGenerator {
    /*
    * anchor_id     1-5000
    * watch_num
    * gift
    * bullet_screen
    * time
    */


    public static List<AnchorOnlineData> fakeAnchorOnlineData(List<Integer> anchorIds){

        List<AnchorOnlineData> realTimeData = new ArrayList<>();

        for(Integer anchorId:anchorIds){
            realTimeData.add(new AnchorOnlineData(
                    anchorId,
                    FakeDataUtils.randomWatchNum(),
                    FakeDataUtils.randomGift(),
                    FakeDataUtils.randomBulletScreen(),
                    new Date()
            ));
        }
        return realTimeData;
    }
}
