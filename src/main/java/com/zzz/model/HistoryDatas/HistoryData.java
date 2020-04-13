package com.zzz.model.HistoryDatas;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzz.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class HistoryData extends BaseEntity<Long> {

    private Integer watchNum;
    private Integer gift;
    private Integer bulletScreen;
    private Integer maxWatchNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data = new Date();
}