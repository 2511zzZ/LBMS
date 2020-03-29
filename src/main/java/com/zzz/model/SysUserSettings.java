package com.zzz.model;

import lombok.Data;

@Data
public class SysUserSettings {

    private Integer employeeId;
    private String setting1;
    private String setting2;

    public SysUserSettings(int employeeId, String setting1, String setting2) {
        this.employeeId = employeeId;
        this.setting1 = setting1;
        this.setting2 = setting2;
    }
}
