package com.zzz.controller;

import com.zzz.model.Structure;
import com.zzz.result.ResponseCode;
import com.zzz.result.Results;
import com.zzz.service.StructureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/structure")
public class StructureController {

    @Autowired
    private StructureService structureService;

    @RequestMapping(method = RequestMethod.GET)
    public Results<Structure> getStructure(@RequestParam int employeeId){
        Structure structure = structureService.getStructure(employeeId);
        return Results.success(ResponseCode.SUCCESS, structure);
    }

}
