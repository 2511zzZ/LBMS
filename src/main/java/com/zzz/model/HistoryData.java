package com.zzz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class HistoryData extends BaseEntity<Long> {

    private Integer anchorId;
    private Integer watchNum;
    private Integer gift;
    private Integer bulletScreen;
    private Integer maxWatchNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data = new Date();
}
