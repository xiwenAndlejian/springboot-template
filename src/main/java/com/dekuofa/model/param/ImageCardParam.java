package com.dekuofa.model.param;

import com.dekuofa.model.enums.BaseStatus;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

/**
 * @author dekuofa <br>
 * @date 2018-09-06 <br>
 */
@Data
public class ImageCardParam {
    @URL(message = "超链接格式异常")
    private String hyperlinks;

    private String     name;
    private String     desc;
    @NotEmpty(message = "图片地址不能为空")
    private String     url;
    @ApiParam(allowEmptyValue = true)
    private BaseStatus status;
}
