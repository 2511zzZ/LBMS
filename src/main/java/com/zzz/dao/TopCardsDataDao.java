package com.zzz.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopCardsDataDao {

    Integer getOnlineWatcher(String tableName, String levelIdName, Integer levelId);

    Integer getGiftSum(String tableName, String levelIdName, Integer levelId);
}
