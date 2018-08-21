package com.dekuofa.controller;

import com.dekuofa.annotation.SysLog;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.service.impl.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gx <br>
 * @date 2018-08-01 <br>
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoServer;

    @SysLog(action = "hello")
    @GetMapping("/hello")
    public RestResponse hello() {
        return RestResponse.ok("hello, world");
    }

    @SysLog(action = "test")
    @GetMapping("/test")
    public RestResponse error() throws Exception {
        throw new RuntimeException("emmm");
    }

//    @GetMapping
//    public void test() {
//        demoServer.test();
//    }
}
