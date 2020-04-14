package com.zzz.service;

import com.zzz.model.Structure;

public interface StructureService {

    Structure getStructure(int employeeId);

    int getEmployeeIdByAnchor(int anchorId);
}
