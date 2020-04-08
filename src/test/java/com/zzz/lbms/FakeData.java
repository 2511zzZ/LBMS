package com.zzz.lbms;

import com.zzz.dao.FakeDataDao;
import com.zzz.fakedata.AnchorGenerator;
import com.zzz.fakedata.SysUserGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FakeData {

    @Autowired
    FakeDataDao fakeDataDao;

    @Test
    void insertAnchors(){
        fakeDataDao.insertAnchors(AnchorGenerator.fakeAnchors());
    }

    @Test
    void insertUserDetails(){
        fakeDataDao.insertUserDetails(SysUserGenerator.fakeUserDetails());
    }

    @Test
    void insertUsers(){
        fakeDataDao.insertUsers(SysUserGenerator.fakeUsers(fakeDataDao.queryAllUserDetails()));
    }
}
