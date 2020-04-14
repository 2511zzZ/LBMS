package com.zzz.fakedata;

import com.zzz.model.AnchorTipOff;
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


    public static List<AnchorOnlineData> fakeAnchorOnlineData(List<Integer> anchorIds, Date datetime){

        List<AnchorOnlineData> realTimeData = new ArrayList<>();

        for(Integer anchorId:anchorIds){
            realTimeData.add(new AnchorOnlineData(
                    anchorId,
                    FakeDataUtils.randomWatchNum(),
                    FakeDataUtils.randomGift(),
                    FakeDataUtils.randomBulletScreen(),
                    datetime
            ));
        }
        return realTimeData;
    }

    /*
    * anchor_id
    * tip_num
    * time
    */
    public static List<AnchorTipOff> fakeAnchorTipOffData(List<Integer> anchorIds, Date datetime){
        List<AnchorTipOff> anchorTipOffData = new ArrayList<>();
        for(Integer anchorId: anchorIds){
            anchorTipOffData.add(new AnchorTipOff(
                    anchorId,
                    FakeDataUtils.randomTipOff(),
                    datetime
            ));
        }
        return anchorTipOffData;
    }
}
