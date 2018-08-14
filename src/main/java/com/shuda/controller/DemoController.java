package com.shuda.controller;

import com.shuda.model.response.RestResponse;
import com.shuda.service.DemoService;
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

    @GetMapping("/hello")
    public RestResponse hello() {
        return RestResponse.ok("hello, world");
    }

    @GetMapping("/error")
    public RestResponse error() {
        throw new RuntimeException("error");
    }

    @GetMapping
    public void test() {
        demoServer.test();
    }
}
