package com.zzz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity<Long> {

    private static final long serialVersionUID = 8925514045582212338L;

    private Integer employeeId;
    private String username;
    private String password;
    private Integer role;

    public SysUser(){}

    public SysUser(Integer employeeId, String username, String password, Integer role){
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
