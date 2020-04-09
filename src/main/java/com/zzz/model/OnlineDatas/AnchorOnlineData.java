package com.zzz.model.OnlineDatas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AnchorOnlineData extends OnlineData {

    private Integer anchorId;
}
