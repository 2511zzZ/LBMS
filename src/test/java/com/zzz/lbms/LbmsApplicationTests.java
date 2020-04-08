package com.zzz.lbms;

import com.zzz.dao.FakeDataDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LbmsApplicationTests {

    @Autowired
    FakeDataDao fakeDataDao;

    @Test
    void contextLoads() {
    }

}
