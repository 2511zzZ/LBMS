package com.zzz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class OnlineData extends BaseEntity<Long> {

    private Integer anchorId;
    private Integer watchNum;
    private Integer gift;
    private Integer bulletScreen;
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date time = new Date();
}
