package com.zzz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "首页")
@RestController
public class SwaggerController {
    @ApiOperation(value = "API 页面", notes = "接口列表")
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public void api(HttpServletResponse response) throws IOException {
        response.sendRedirect("doc.html");
    }
}