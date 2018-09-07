package com.dekuofa.service.impl;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.model.entity.ScrollImage;
import com.dekuofa.model.enums.BaseStatus;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.ScrollImageService;
import io.github.biezhi.anima.core.AnimaQuery;
import io.github.biezhi.anima.page.Page;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Objects;

import static io.github.biezhi.anima.Anima.delete;
import static io.github.biezhi.anima.Anima.select;

/**
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
@Service
public class ScrollImageServiceImpl implements ScrollImageService {


    @Override
    public Page<ScrollImage> query(PageParam pageParam, BaseStatus... status) {
        AnimaQuery<ScrollImage> query = select().from(ScrollImage.class);
        if (Objects.nonNull(status) && status.length > 0) {
            query.in(ScrollImage::getStatus, Arrays.asList(status));
        }
        return query.page(pageParam.getPage(), pageParam.getLimit());
    }

    @Override
    public int countById(Integer id) {
        return (int) select().from(ScrollImage.class)
                .where(ScrollImage::getId).eq(id).count();
    }

    @Transient
    @Override
    public Integer save(ScrollImage scrollImage) {
        Integer id = scrollImage.save().asInt();
        if (id == null) {
            throw new TipException("主键获取异常");
        }
        return id;
    }

    @Transient
    @Override
    public void modify(ScrollImage scrollImage) {
        scrollImage.update();
    }

    @Transient
    @Override
    public void deleteById(Integer id) {
        delete().from(ScrollImage.class).byId(id);
    }

    @Override
    public Page<ScrollImage> list() {
        PageParam param = new PageParam()
                .page(Constants.FIRST_PAGE_NUM)
                .limit(Constants.DEFAULT_MAX_PAGE_SIZE);
        return this.query(param);
    }
}
