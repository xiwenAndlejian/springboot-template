package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.EnumMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dekuofa <br>
 * @date 2018-09-06 <br>
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ImageCard extends Model implements BaseEntity {

    private Integer id;
    private String  name;
    private String  desc;
    private String  url;
    private String  hyperlinks;
    /**
     * 状态 normal=正常,deleted=已删除
     */
    @EnumMapping("getCode")
    private String  status;

    private Long    createTime;
    private Long    modifyTime;
    private Integer creatorId;
    private String  creatorName;
    private Integer modifierId;
    private String  modifierName;
}
