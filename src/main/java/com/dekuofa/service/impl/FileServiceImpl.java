package com.dekuofa.service.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.model.entity.FileInfo;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.FileService;
import io.github.biezhi.anima.core.AnimaQuery;
import io.github.biezhi.anima.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.beans.Transient;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author dekuofa <br>
 * @date 2018-09-03 <br>
 */
@Service
public class FileServiceImpl implements FileService {
    @Transient
    @Override
    public Integer save(FileInfo fileInfo) {
        Integer id = fileInfo.save().asInt();
        if (id == null) {
            throw new TipException("主键获取失败");
        }
        return id;
    }

    @Override
    public Page<FileInfo> query(PageParam pageParam, String keyword) {
        AnimaQuery<FileInfo> query = select().from(FileInfo.class);
        if (!StringUtils.isEmpty(keyword)) {
            query.where(FileInfo::getName).like("%" + keyword + "%");
        }
        return query.page(pageParam.getPage(), pageParam.getLimit());
    }

    @Override
    public void modify(FileInfo fileInfo) {
        //todo 修改文件信息（状态，名称）
    }
}
