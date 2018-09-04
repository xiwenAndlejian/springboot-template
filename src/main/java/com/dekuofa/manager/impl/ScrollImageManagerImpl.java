package com.dekuofa.manager.impl;

import com.dekuofa.constant.Constants;
import com.dekuofa.exception.TipException;
import com.dekuofa.manager.ScrollImageManager;
import com.dekuofa.model.common.Status;
import com.dekuofa.model.entity.ScrollImage;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.ScrollImageService;
import io.github.biezhi.anima.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
@Component
public class ScrollImageManagerImpl implements ScrollImageManager {

    private ScrollImageService scrollImageService;

    @Autowired
    public ScrollImageManagerImpl(ScrollImageService scrollImageService) {
        this.scrollImageService = scrollImageService;
    }

    @Override
    public Integer save(ScrollImage scrollImage) {
        try {
            return scrollImageService.save(scrollImage);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof TipException) {
                throw e;
            } else {
                throw new TipException("数据库执行异常");
            }
        }
    }

    @Override
    public void modify(ScrollImage scrollImage) {
        try {
            scrollImageService.modify(scrollImage);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof TipException) {
                throw e;
            } else {
                throw new TipException("数据库执行异常");
            }
        }
    }

    @Override
    public void delete(ScrollImage scrollImage) {
        try {
            scrollImageService.delete(scrollImage);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof TipException) {
                throw e;
            } else {
                throw new TipException("数据库执行异常");
            }
        }
    }

    @Override
    public Page<ScrollImage> query(PageParam pageParam, String... status) {
        return scrollImageService.query(pageParam, status);
    }

    @Override
    public Page<ScrollImage> listAllOfNormal() {
        PageParam param = new PageParam()
                .page(Constants.FIRST_PAGE_NUM)
                .limit(Constants.DEFAULT_MAX_PAGE_SIZE);
        return scrollImageService.query(param, Status.NORMAL);
    }
}
