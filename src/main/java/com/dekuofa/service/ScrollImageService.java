package com.dekuofa.service;

import com.dekuofa.model.entity.ScrollImage;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;

/**
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
public interface ScrollImageService extends BaseService<ScrollImage, Integer> {

    Page<ScrollImage> query(PageParam pageParam, String... status);

    int countById(Integer id);

}
