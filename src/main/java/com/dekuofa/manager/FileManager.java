package com.dekuofa.manager;

import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.FileInfo;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dekuofa <br>
 * @date 2018-09-03 <br>
 */
public interface FileManager {
    FileInfo upload(MultipartFile file, String fileName, UserInfo userInfo);

    Page<FileInfo> query(PageParam pageParam, String keyword);
}
