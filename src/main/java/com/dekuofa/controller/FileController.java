package com.dekuofa.controller;

import com.dekuofa.exception.TipException;
import com.dekuofa.manager.FileManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.FileInfo;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.response.RestResponse;
import io.github.biezhi.anima.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 处理文件上传&下载
 *
 * @author dekuofa <br>
 * @date 2018-09-03 <br>
 */
@RestController
@Slf4j
public class FileController {
    private FileManager fileManager;

    @Autowired
    public FileController(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @PostMapping("/upload")
    public RestResponse<?> upload(
            @ModelAttribute(binding = false) UserInfo userInfo,
            @RequestParam("file_name") String fileName,
            MultipartFile file) {
        log.debug("当前用户：" + userInfo);
        if (file == null) {
            return RestResponse.fail("文件上传失败：上传文件不能为空");
        }
        try {
            FileInfo fileInfo = fileManager.upload(file, fileName, userInfo);
            return RestResponse.ok(fileInfo.toUploadResponse());
        } catch (Exception e) {
            String msg = "服务器异常：文件上传失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                e.printStackTrace();
            }
            return RestResponse.fail(msg);
        }
    }

    @GetMapping("/fileInfo")
    public RestResponse<?> query(String keyword, PageParam pageParam) {
        try {
            Page<FileInfo> payload = fileManager.query(pageParam, keyword);
            return RestResponse.ok(payload);
        } catch (Exception e) {
            String msg = "查询失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            }
            return RestResponse.fail("服务异常：" + msg);
        }
    }
}