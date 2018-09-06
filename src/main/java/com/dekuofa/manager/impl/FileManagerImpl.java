package com.dekuofa.manager.impl;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.manager.FileManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.common.Status;
import com.dekuofa.model.entity.FileInfo;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.FileService;
import com.dekuofa.utils.DateUtil;
import com.dekuofa.utils.FileUtil;
import com.dekuofa.utils.UuidUtil;
import io.github.biezhi.anima.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author dekuofa <br>
 * @date 2018-09-03 <br>
 */
@Component
@Slf4j
public class FileManagerImpl implements FileManager {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    private FileService fileService;

    @Autowired
    public FileManagerImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public FileInfo upload(MultipartFile fileUpload, String fileName, UserInfo userInfo) {
        // todo 文件上传
        // 获取当前日期
        String day = DateUtil.newDate();
        // 如果获取不到，则使用默认文件类型
        String fileType =
                FileUtil.getFileType(fileUpload).orElse(Constants.DEFAULT_FILE_TYPE);
        // 根据当前日期创建文件夹
        File dir = FileUtil.createDir(fileUploadPath, day)
                .orElseThrow(() -> new TipException("上传文件失败：创建文件夹失败，请联系管理员！"));
        // 文件实际存储名称uuid
        String uuid = UuidUtil.getRandomUUIDString();
        // 创建文件对象
        File file = new File(dir.getPath() + "/" + uuid);
        try {
            // 写入硬盘
            FileUtils.writeByteArrayToFile(
                    file,
                    fileUpload.getBytes()
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new TipException("文件写入失败");
        }
        Double size = FileUtil.getFileSizeOfMB(fileUpload);
        // 文件路径
        String url = "/" + day + "/" + uuid;

        FileInfo fileInfo =
                new FileInfo(fileName, uuid, url, size, fileType, Status.NORMAL);
        Long now = DateUtil.newUnixMilliSecond();
        fileInfo.setModifyInfo(userInfo, now)
                .setCreateInfo(userInfo, now);

        fileService.save(fileInfo);
        // 日志打印
        log.debug("文件名：" + fileName);
        log.debug("文件路径：" + dir.getPath());
        log.debug("uuid：" + uuid);
        log.debug("文件大小：" + size + "MB");
        log.debug("文件类型：" + fileType);
        return fileInfo;
    }

    @Override
    public Page<FileInfo> query(PageParam pageParam, String keyword) {
        return fileService.query(pageParam, keyword);
    }
}
