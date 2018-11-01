package com.dekuofa.controller;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.manager.ScrollImageManager;
import com.dekuofa.model.NormalUserInfo;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.ScrollImage;
import com.dekuofa.model.enums.BaseStatus;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.param.ScrollImageParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.DateUtil;
import io.github.biezhi.anima.page.Page;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
@RestController
public class ScrollImageController implements BaseController {

    private ScrollImageManager scrollImageManager;

    @Autowired
    public ScrollImageController(ScrollImageManager scrollImageManager) {
        this.scrollImageManager = scrollImageManager;
    }

    @RequiresAuthentication
    @PostMapping("/scrollImage")
    public RestResponse<?> save(UserInfo userInfo, @Valid ScrollImageParam param) {
        Long now = DateUtil.newUnixMilliSecond();
        ScrollImage image = new ScrollImage(param)
                .setModifyInfo(userInfo, now).setCreateInfo(userInfo, now);
        image.setStatus(BaseStatus.NORMAL);
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

    @RequiresAuthentication
    @DeleteMapping("/scrollImage/{id}")
    public RestResponse<?> delete(UserInfo userInfo,
                                  @PathVariable("id") Integer id) {

        if (!scrollImageManager.isExist(id)) {
            return RestResponse.fail("删除失败：更新对象不存在");
        }
        try {
            scrollImageManager.delete(id);
            return RestResponse.ok();
        } catch (Exception e) {
            String msg = Constants.ERROR_MESSAGE;
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                e.printStackTrace();
            }
            return RestResponse.fail("删除失败：" + msg);
        }
    }

    @RequiresAuthentication
    @PutMapping("/scrollImage/{id}")
    public RestResponse<?> modify(NormalUserInfo normalUserInfo,
                                  @PathVariable("id") Integer id,
                                  @Valid ScrollImageParam param) {

        if (!scrollImageManager.isExist(id)) {
            return RestResponse.fail("更新失败：更新对象不存在");
        }

        ScrollImage scrollImage = new ScrollImage(param);
        scrollImage.setId(id);
        scrollImage.setModifyInfo(normalUserInfo, DateUtil.newUnixMilliSecond());
        try {
            scrollImageManager.modify(scrollImage);
            return RestResponse.ok();
        } catch (Exception e) {
            String msg = Constants.ERROR_MESSAGE;
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                e.printStackTrace();
            }
            return RestResponse.fail("更新失败：" + msg);
        }
    }

    @RequiresAuthentication
    @GetMapping("/scrollImage")
    public RestResponse<?> query(PageParam pageParam,
                                 @ApiParam(allowEmptyValue = true) BaseStatus status) {
        try {
            Page<ScrollImage> payload = scrollImageManager.query(pageParam, status);
            return RestResponse.ok(payload);
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail("查询失败：" + msg);
        }
    }

    @GetMapping("/scrollImage/list")
    public RestResponse<?> listAllOfNormal() {
        try {
            Page<ScrollImage> payload = scrollImageManager.listAllOfNormal();
            return RestResponse.ok(payload);
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail("获取滚动图列表失败：" + msg);
        }
    }
}
