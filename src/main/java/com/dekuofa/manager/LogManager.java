package com.dekuofa.manager;

import com.dekuofa.model.entity.SysLogInfo;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;

/**
 * @author dekuofa <br>
 * @date 2018-08-29 <br>
 */
public interface LogManager {

    void save(SysLogInfo logInfo);

    Page<SysLogInfo> query(PageParam pageParam);
}
