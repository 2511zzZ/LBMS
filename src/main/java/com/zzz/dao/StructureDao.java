package com.zzz.dao;

import com.zzz.model.Anchor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StructureDao {

    Integer getTotalId(int employeeId);

    List<Integer> getBranchIds(int employeeId);

    Integer getBranchId(int employeeId);

    List<Integer> getGroupIds(int branchId);

    Integer getGroupId(int employeeId);

    Integer getSuperBranchEmployeeId(int group_id);

    List<Integer> getTeamIds(int group_id);

    Integer getTeamId(int employeeId);

    Integer getSuperGroupEmployeeId(int teamId);

    List<Integer> getAnchorIds(int teamId);
}
