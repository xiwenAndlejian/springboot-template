package com.dekuofa.model.param;

import com.dekuofa.model.enums.BaseStatus;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
@Data
public class ScrollImageParam {
    @NotEmpty(message = "图片url不能为空")
    private String  url;
    @NotEmpty(message = "文件id不能为空")
    private Integer fileId;
    @NotEmpty(message = "图片类型不能为空")
    private String  type;
    /**
     * 超链接
     */
    private String  hyperlinks;
    /**
     * 排序
     */
    @NotNull(message = "排序值不能为空")
    private Integer order;
    @ApiParam(allowEmptyValue = true)
    private BaseStatus status;
}
