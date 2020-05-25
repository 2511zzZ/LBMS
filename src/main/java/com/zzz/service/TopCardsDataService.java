package com.zzz.service;

import com.zzz.model.SysUser;

public interface TopCardsDataService {

    Integer getOnlineAnchorNum(SysUser user);

    Integer getOnlineWatcher(SysUser user);

    Integer getGiftSum(SysUser user);
}
