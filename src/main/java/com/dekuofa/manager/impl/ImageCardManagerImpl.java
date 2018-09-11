package com.dekuofa.manager.impl;

import com.dekuofa.manager.ImageCardManager;
import com.dekuofa.model.entity.ImageCard;
import com.dekuofa.model.param.PageParam;
import com.dekuofa.service.ImageCardService;
import io.github.biezhi.anima.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dekuofa <br>
 * @date 2018-09-11 <br>
 */
@Component
public class ImageCardManagerImpl implements ImageCardManager {

    private ImageCardService imageCardService;

    @Autowired
    public ImageCardManagerImpl(ImageCardService imageCardService) {
        this.imageCardService = imageCardService;
    }

    @Override
    public Integer save(ImageCard imageCard) {
        return imageCardService.save(imageCard);
    }

    @Override
    public void modify(ImageCard imageCard) {
        imageCardService.modify(imageCard);
    }

    @Override
    public Page<ImageCard> query(PageParam pageParam) {
        return imageCardService.query(pageParam);
    }

    @Override
    public List<ImageCard> list() {
        return imageCardService.list().getRows();
    }
}
