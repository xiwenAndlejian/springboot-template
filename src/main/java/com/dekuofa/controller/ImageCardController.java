package com.dekuofa.controller;

import com.dekuofa.manager.ImageCardManager;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.entity.ImageCard;
import com.dekuofa.model.enums.BaseStatus;
import com.dekuofa.model.param.ImageCardParam;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.model.response.RestResponse;
import com.dekuofa.utils.DateUtil;
import io.github.biezhi.anima.page.Page;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author dekuofa <br>
 * @date 2018-09-11 <br>
 */
@RestController
public class ImageCardController implements BaseController {
    private ImageCardManager imageCardManager;

    @Autowired
    public ImageCardController(ImageCardManager imageCardManager) {
        this.imageCardManager = imageCardManager;
    }

    @PostMapping("/imageCard")
    public RestResponse<?> save(@Valid @RequestBody ImageCardParam param,
                                @ApiParam(hidden = true) UserInfo userInfo) {
        ImageCard imageCard = new ImageCard(param);
        imageCard.setStatus(BaseStatus.INTI);
        Long now = DateUtil.newUnixMilliSecond();
        imageCard.setCreateInfo(userInfo, now)
                .setModifyInfo(userInfo, now);
        try {
            Integer id = imageCardManager.save(imageCard);

            return RestResponse.ok(id);
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg);
        }
    }

    @GetMapping("/imageCard")
    public RestResponse<?> query(PageParam param) {
        try {
            Page<ImageCard> result = imageCardManager.query(param);
            return RestResponse.ok(result);
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg);
        }
    }

    @GetMapping("/imageCard/list")
    public RestResponse<?> list() {
        try {
            List<ImageCard> result = imageCardManager.list();
            return RestResponse.ok(result);
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg);
        }
    }

    @PutMapping("/imageCard/{id}")
    public RestResponse<?> modify(@PathVariable @ApiParam(hidden = true) Integer id,
                                  @ApiParam(hidden = true) UserInfo userInfo,
                                  @Valid @RequestBody ImageCardParam param) {
        try {
            ImageCard imageCard = new ImageCard(param);
            imageCard.setModifyInfo(userInfo, DateUtil.newUnixMilliSecond());
            imageCard.setId(id);
            imageCardManager.modify(imageCard);

            return RestResponse.ok();
        } catch (Exception e) {
            String msg = getErrorMessage(e);
            return RestResponse.fail(msg);
        }
    }
}
