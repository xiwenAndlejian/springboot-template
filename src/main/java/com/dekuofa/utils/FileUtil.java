package com.dekuofa.utils;

import com.dekuofa.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dekuofa <br>
 * @date 2018-09-03 <br>
 */
@Slf4j
public class FileUtil {

    /**
     * 文件类型
     */
    private static String FILE_TYPE_REGEX = ".(\\w+)$";

    public static Optional<File> createDir(String path, String dirName) {
        final String dirPath = path + "/" + dirName;
        File         dir     = new File(dirPath);
        if (!dir.exists()) {
            boolean isSuccess = dir.mkdir();
            if (!isSuccess) {
                log.error("创建文件夹失败：" + dirPath);
                return Optional.empty();
            }
        }
        return Optional.of(dir);
    }

    public static Double getFileSizeOfMB(MultipartFile file) {
        Long size = file.getSize();
        return new BigDecimal(size)
                .setScale(2)
                .divide(new BigDecimal(Constants.SIZE_OF_MB), BigDecimal.ROUND_UP)
                .doubleValue();
    }

    public static Optional<String> getFileType(MultipartFile file) {
        String  fileName = file.getOriginalFilename();
        Pattern pattern  = Pattern.compile(FILE_TYPE_REGEX);
        Matcher matcher  = pattern.matcher(fileName);
        if (matcher.find()) {
            return Optional.of(matcher.group(1));
        }
        return Optional.empty();
    }
}
