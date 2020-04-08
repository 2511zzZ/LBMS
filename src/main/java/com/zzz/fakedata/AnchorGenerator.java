package com.zzz.fakedata;

import com.zzz.model.Anchor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnchorGenerator {
    /*
    * anchor_id     1-5000
    * room_id       六位数
    * name
    * nickname
    * phone     1-3/5/8-*********
    */

    public static List<Anchor> fakeAnchors(){
        List<Anchor> anchors = new ArrayList<>();
//        Anchor anchor1 = new Anchor(11, 123456, "主播1", "主播1","13456789098");
//        Anchor anchor2 = new Anchor(12, 123654, "主播2", "主播2","13098765432");
//        anchors.add(anchor1);
//        anchors.add(anchor2);

        for(int i=1;i<=FakeDataConfig.anchorNum;i++){
            String name = FakeDataUtils.randomName();
            anchors.add(new Anchor(
                    i,
                    FakeDataUtils.randomRoomId(),
                    name,
                    name,
                    FakeDataUtils.randomPhone()
            ));
        }

        return anchors;
    }

}
