package com.zzz.model;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Anchor extends BaseEntity<Long> {

    private Integer anchorId;
    private Integer roomId;
    private String name;
    private String nickname;
    private String phone;

}
