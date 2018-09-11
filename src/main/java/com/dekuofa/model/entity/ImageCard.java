package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import com.dekuofa.model.enums.BaseStatus;
import com.dekuofa.model.param.ImageCardParam;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.EnumMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.InvocationTargetException;

/**
 * @author dekuofa <br>
 * @date 2018-09-06 <br>
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ImageCard extends Model implements BaseEntity {

    private Integer    id;
    private String     name;
    private String     desc;
    private String     url;
    private String     hyperlinks;
    /**
     * 状态 normal=正常,deleted=已删除
     */
    @EnumMapping("getCode")
    private BaseStatus status;

    private Long    createTime;
    private Long    modifyTime;
    private Integer creatorId;
    private String  creatorName;
    private Integer modifierId;
    private String  modifierName;

    public ImageCard() {
    }

    public ImageCard(ImageCardParam param) {
        this.hyperlinks = param.getHyperlinks();
        this.name = param.getName();
        this.desc = param.getDesc();
        this.url = param.getUrl();
    }

}
