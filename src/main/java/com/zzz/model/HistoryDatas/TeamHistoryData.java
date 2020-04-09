package com.zzz.model.HistoryDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeamHistoryData extends HistoryData {

    private Integer teamId;
}
