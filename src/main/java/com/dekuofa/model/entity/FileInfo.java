package com.dekuofa.model.entity;

import com.dekuofa.model.response.FileUploadResponse;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author dekuofa <br>
 * @date 2018-09-03 <br>
 */
@Table(name = "t_file_info")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class FileInfo extends Model {

    private Integer id;
    private String  name;
    private String  uuid;
    private String  url;
    private Double  size;
    private String  type;
    private Long    createTime;
    private Long    modifyTime;
    private Integer creatorId;
    private String  creatorName;
    private Integer modifierId;
    private String  modifierName;
    private String  status;

    public FileInfo(String name, String uuid, String url, Double size, String type, String status) {
        this.name = name;
        this.uuid = uuid;
        this.url = url;
        this.size = size;
        this.type = type;
        this.status = status;
    }

    public FileUploadResponse toUploadResponse() {
        return new FileUploadResponse().fileId(id).url(url);
    }

}
