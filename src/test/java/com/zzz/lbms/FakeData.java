package com.zzz.lbms;

import com.zzz.service.fakedata.FakeDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FakeData {

    @Autowired
    FakeDataService fakeDataService;

    @Test
    void insertAnchors(){
        fakeDataService.insertAnchors();
    }

    @Test
    void insertUserDetails(){
        fakeDataService.insertUserDetails();
    }

    @Test
    void insertUsers(){
        fakeDataService.insertUsers();
    }

    @Test
    void insertStructures() {fakeDataService.insertStructures(); }

    @Test
    void insertStruManage() {fakeDataService.insertStrucManage();}

}
