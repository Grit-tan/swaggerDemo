package com.grit.learning.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/helloWorld")
public class HelloWorldController {

    @ApiOperation(value = "hello接口,接口名称", notes = "描述接口的作用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户账号", required = true, defaultValue = "grit",
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "query",
                    dataType = "int")
    })
    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello(@RequestParam String userAccount, int age) {
        return userAccount + " is " + age + " old ";
    }
}
