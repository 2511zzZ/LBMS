package com.zzz.controller;

import com.zzz.model.Level;
import com.zzz.model.Structure;
import com.zzz.model.SysUser;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.StructureService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("structure")
public class StructureController {

    @Autowired
    private StructureService structureService;

    @RequestMapping(method = RequestMethod.GET)
    public Results<Structure> getStructure(@RequestParam int employeeId){
        Structure structure = structureService.getStructure(employeeId);
        return Results.success(ResponseCode.SUCCESS, structure);
    }

}
