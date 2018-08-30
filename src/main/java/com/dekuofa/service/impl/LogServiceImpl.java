package com.dekuofa.service.impl;

import com.dekuofa.model.entity.SysLogInfo;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.LogService;
import io.github.biezhi.anima.page.Page;
import org.springframework.stereotype.Service;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author dekuofa <br>
 * @date 2018-08-29 <br>
 */
@Service
public class LogServiceImpl implements LogService {

    @Override
    public void save(SysLogInfo log) {
        log.save();
    }

    @Override
    public Page<SysLogInfo> query(PageParam pageParam) {
        return select().from(SysLogInfo.class).page(pageParam.getPage(), pageParam.getLimit());
    }
}
