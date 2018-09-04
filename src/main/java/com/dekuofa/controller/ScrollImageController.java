package com.dekuofa.controller;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.manager.ScrollImageManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.ScrollImage;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.ScrollImageParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.DateUtil;
import io.github.biezhi.anima.page.Page;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
@RestController
public class ScrollImageController {

    private ScrollImageManager scrollImageManager;

    @Autowired
    public ScrollImageController(ScrollImageManager scrollImageManager) {
        this.scrollImageManager = scrollImageManager;
    }

    @RequiresAuthentication
    @PostMapping("/scrollImage")
    public RestResponse<?> save(UserInfo userInfo, @Valid ScrollImageParam param) {

        ScrollImage image = new ScrollImage(param);

        Long now = DateUtil.newUnixMilliSecond();
        image.setCreateTime(now);
        image.setModifyTime(now);
        image.setCreatorId(userInfo.getUserId());
        image.setModifierId(userInfo.getUserId());
        image.setCreatorName(userInfo.getNickName());
        image.setModifierName(userInfo.getNickName());

        try {
            Integer id = scrollImageManager.save(image);
            return RestResponse.ok(id);
        } catch (Exception e) {
            String msg = Constants.ERROR_MESSAGE;
            if (e instanceof TipException) {
                msg = e.getMessage();
            }
            return RestResponse.fail("新增失败：" + msg);
        }
    }

    @GetMapping("/scrollImage")
    public RestResponse<?> query(PageParam pageParam,
                                 @ApiParam("normal=正常,deleted=已删除,baned=被禁用")
                                         String status) {
        try {
            Page<ScrollImage> payload = scrollImageManager.query(pageParam, status);
            return RestResponse.ok(payload);
        } catch (Exception e) {
            String msg = Constants.ERROR_MESSAGE;
            if (e instanceof TipException) {
                msg = e.getMessage();
            }
            return RestResponse.fail("查询失败：" + msg);
        }
    }
}
