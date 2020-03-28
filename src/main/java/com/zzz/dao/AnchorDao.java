package com.zzz.dao;

import com.zzz.model.Anchor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnchorDao {
    List<Anchor> getAnchorWithPagination(int offset, int limit);

    Integer getTotalNum();

    List<Anchor> getOnlineAnchorWithPagination(int offset, int limit);

    Integer getOnlineTotalNum();
}