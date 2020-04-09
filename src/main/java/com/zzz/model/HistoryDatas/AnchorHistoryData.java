package com.zzz.model.HistoryDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnchorHistoryData extends HistoryData {

    private Integer anchorId;
}
