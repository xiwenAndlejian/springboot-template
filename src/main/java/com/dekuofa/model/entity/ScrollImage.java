package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import com.dekuofa.model.param.ScrollImageParam;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 滚动图
 *
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "t_scroll_image")
public class ScrollImage extends Model implements BaseEntity {
    private Integer id;
    /**
     * 图片地址
     */
    private String  url;
    private Integer fileId;
    private String  type;
    /**
     * 超链接
     */
    private String  hyperlinks;
    /**
     * 排序
     */
    private Integer order;
    @ApiParam(allowableValues = "normal=正常,deleted=已删除,baned=被禁用")
    private String  status;
    private Long    createTime;
    private Long    modifyTime;
    private Integer creatorId;
    private String  creatorName;
    private Integer modifierId;
    private String  modifierName;

    public ScrollImage() {
    }

    public ScrollImage(ScrollImageParam param) {
        this.type = param.getType();
        this.order = param.getOrder();
        this.fileId = param.getFileId();
        this.hyperlinks = param.getHyperlinks();
    }
}
