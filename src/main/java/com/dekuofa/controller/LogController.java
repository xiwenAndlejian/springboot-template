package com.dekuofa.controller;

import com.dekuofa.manager.LogManager;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dekuofa <br>
 * @date 2018-08-30 <br>
 */
@RestController
public class LogController {

    private LogManager logManager;

    @Autowired
    public LogController(LogManager logManager) {
        this.logManager = logManager;
    }

    @GetMapping("/log")
    public RestResponse<?> query(PageParam pageParam) {
        return RestResponse.ok(logManager.query(pageParam));
    }
}
