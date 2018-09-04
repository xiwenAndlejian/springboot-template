package com.dekuofa.service;

import com.dekuofa.model.entity.FileInfo;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;

/**
 * @author dekuofa <br>
 * @date 2018-09-03 <br>
 */
public interface FileService {

    Integer save(FileInfo fileInfo);

    Page<FileInfo> query(PageParam pageParam, String keyword);

    void modify(FileInfo fileInfo);
}
