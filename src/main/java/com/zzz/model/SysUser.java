package com.zzz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity<Long> {

    private static final long serialVersionUID = 8925514045582212338L;

    private Integer employeeId;
    private String username;
    private String name;
    private String gender;
    private String nickname;
    private String avatar;
    private Integer role;

    public SysUser(int employeeId, String username){
        this.employeeId = employeeId;
        this.username = username;
    }

    @Override
    public String toString(){
        return employeeId + " " + name + " " + gender;
    }
}
