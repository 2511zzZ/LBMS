package com.zzz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDetails extends BaseEntity<Long> {

    private static final long serialVersionUID = 8925514045582212338L;

    private Integer employeeId;
    private String username;
    private String name;
    private String gender;
    private String nickname;
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
