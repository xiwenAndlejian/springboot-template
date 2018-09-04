package com.dekuofa.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dekuofa <br>
 * @date 2018-09-03 <br>
 */
@Data
@NoArgsConstructor
public class FileUploadResponse {
    private Integer fileId;
    private String  url;

    public FileUploadResponse fileId(Integer fileId) {
        this.fileId = fileId;
        return this;
    }

    public FileUploadResponse url(String url) {
        this.url = url;
        return this;
    }

}
