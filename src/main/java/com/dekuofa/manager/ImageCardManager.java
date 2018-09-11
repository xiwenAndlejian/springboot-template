package com.dekuofa.manager;

import com.dekuofa.model.entity.ImageCard;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;

import java.util.List;

/**
 * @author dekuofa <br>
 * @date 2018-09-11 <br>
 */
public interface ImageCardManager {

    Integer save(ImageCard imageCard);

    void modify(ImageCard imageCard);

    Page<ImageCard> query(PageParam pageParam);

    List<ImageCard> list();
}
