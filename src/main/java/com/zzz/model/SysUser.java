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
    private int role;

}
