package com.dekuofa.service.impl;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.model.entity.ImageCard;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.ImageCardService;
import io.github.biezhi.anima.page.Page;
import org.springframework.stereotype.Service;


import static io.github.biezhi.anima.Anima.delete;
import static io.github.biezhi.anima.Anima.select;


/**
 * @author dekuofa <br>
 * @date 2018-09-11 <br>
 */
@Service
public class ImageCardServiceImpl implements ImageCardService {
    @Override
    public Page<ImageCard> query(PageParam pageParam) {
        return select().from(ImageCard.class)
                .page(pageParam.getPage(), pageParam.getLimit());
    }

    @Override
    public Integer save(ImageCard imageCard) {
        Integer id = imageCard.save().asInt();
        if (id == null) {
            throw new TipException("主键获取失败");
        }
        return id;
    }

    @Override
    public void modify(ImageCard imageCard) {
        imageCard.update();
    }

    @Override
    public void deleteById(Integer id) {
        delete().from(ImageCard.class).byId(id);
    }

    /**
     * 限制了获取的信息条数，目前为固定值 10
     */
    @Override
    public Page<ImageCard> list() {
        return select().from(ImageCard.class)
                .page(Constants.DEFAULT_PAGE_NUM, Constants.FIRST_PAGE_NUM);
    }
}
