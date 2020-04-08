package com.zzz.model;

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

    public Anchor(){}

    public Anchor(int anchorId, int roomId, String name, String nickname, String phone){
        this.anchorId = anchorId;
        this.roomId = roomId;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
    }

}
