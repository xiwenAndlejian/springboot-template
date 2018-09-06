package com.dekuofa.manager;

import com.dekuofa.model.entity.ScrollImage;
import com.dekuofa.model.param.PageParam;
import io.github.biezhi.anima.page.Page;

/**
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
public interface ScrollImageManager {

    Integer save(ScrollImage scrollImage);

    void modify(ScrollImage scrollImage);

    void delete(Integer id);

    Page<ScrollImage> query(PageParam pageParam, String... status);

    Page<ScrollImage> listAllOfNormal();

    boolean isExist(Integer id);
}
