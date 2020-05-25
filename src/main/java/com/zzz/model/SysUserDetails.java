package com.zzz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDetails extends BaseEntity<Long> {

    private static final long serialVersionUID = 8925514045582212338L;

    private Integer employeeId;
    private String username;    // 用户名 拼音
    private String name;        // 姓名 汉字
    private String gender;
    private String nickname;    // 昵称 用户自定义
    private String avatar;
    private Integer role;

    public SysUserDetails(){}

    public SysUserDetails(int employeeId, String username){
        this.employeeId = employeeId;
        this.username = username;
    }

    public SysUserDetails(int employeeId, String username, String name, String gender, String nickname, String avatar, int role){
        this.employeeId = employeeId;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.nickname = nickname;
        this.avatar = avatar;
        this.role = role;
    }

    @Override
    public String toString(){
        return employeeId + " " + name + " " + gender;
    }
}
