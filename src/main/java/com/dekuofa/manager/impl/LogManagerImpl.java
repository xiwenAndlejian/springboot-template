package com.dekuofa.manager.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.LogManager;
import com.dekuofa.model.entity.SysLogInfo;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.LogService;
import io.github.biezhi.anima.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dekuofa <br>
 * @date 2018-08-29 <br>
 */
@Component
@Slf4j
public class LogManagerImpl implements LogManager {
    private LogService logService;

    @Autowired
    public LogManagerImpl(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void save(SysLogInfo logInfo) {
        try {
            logService.save(logInfo);
        } catch (Exception e) {
            log.error("插入日志失败");
            throw new TipException("日志插入失败", e.getCause());
        }

    }

    @Override
    public Page<SysLogInfo> query(PageParam pageParam) {
        return logService.query(pageParam);
    }
}
