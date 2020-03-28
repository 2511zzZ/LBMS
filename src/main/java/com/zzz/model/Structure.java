package com.zzz.model;

import lombok.Data;

import java.util.List;

@Data
public class Structure {
    private Integer employeeId;
    private Integer levelId;   // 组号
    private Integer superior;   //直接上级 组号
    private List<Integer> subordinates;     //直接下级 组号 List
    private Level level;
}
