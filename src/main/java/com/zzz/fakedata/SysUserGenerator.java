package com.zzz.fakedata;

import com.zzz.lbms.Utils;
import com.zzz.model.SysUser;
import com.zzz.model.SysUserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SysUserGenerator {
    /*
    * sys_user:
    * employee_id     1-556
    * username
    * password      md5加密
    * role      1*1,2*5,3*50,4*500
    *
    * sys_user_details
    * employee_id
    * username
    * name
    * gender
    * nickname
    * avatar
    * role
    */

    public static List<SysUserDetails> fakeUserDetails(){
        List<SysUserDetails> userDetailsList = new ArrayList<>();

        Set<String> names = new HashSet<>();

        for(int i=1;i<=FakeDataConfig.userNum;i++) {
            int role = 4;
            if (i == 1) {
                role = 1;
            } else if (i <= 6) {
                role = 2;
            } else if (i <= 56) {
                role = 3;
            }

            String name = FakeDataUtils.randomName();

            // 保证name和username不重复
            while(names.contains(name)){
                name = FakeDataUtils.randomName();
            }
            names.add(name);

            SysUserDetails userDetails = new SysUserDetails(
                    i,
                    FakeDataUtils.toPinyin(name),
                    name,
                    FakeDataUtils.judgeGender(name),
                    name,
                    "default.jpg",
                    role
            );
            userDetailsList.add(userDetails);
        }
        return userDetailsList;
    }

    public static List<SysUser> fakeUsers(List<SysUserDetails> userDetailsList){
        List<SysUser> userList = new ArrayList<>();
        for(SysUserDetails userDetails:userDetailsList){
            SysUser user = new SysUser(
                    userDetails.getEmployeeId(),
                    userDetails.getUsername(),
                    Utils.DoMD5("123456"),
                    userDetails.getRole()
            );
            userList.add(user);
        }
        return userList;
    }

}
