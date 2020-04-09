package com.zzz.model.HistoryDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TotalHistoryData extends HistoryData {

    private Integer totalId;
}
