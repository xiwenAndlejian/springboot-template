package com.dekuofa.service;

import com.dekuofa.model.entity.ImageCard;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;

/**
 * @author dekuofa <br>
 * @date 2018-09-11 <br>
 */
public interface ImageCardService extends BaseService<ImageCard, Integer> {
    Page<ImageCard> query(PageParam pageParam);
}
