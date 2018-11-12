package com.dekuofa.service.impl;

import com.dekuofa.exception.TipException;
import com.dekuofa.model.entity.UserDetail;
import com.dekuofa.service.UserDetailService;
import io.github.biezhi.anima.page.Page;
import org.springframework.stereotype.Service;

import static io.github.biezhi.anima.Anima.select;

/**
 * @author dekuofa <br>
 * @date 2018-11-07 <br>
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Override
    public Integer save(UserDetail userDetail) {
        return userDetail.save().asInt();
    }

    @Override
    public void modify(UserDetail userDetail) {
        userDetail.update();
    }

    @Override
    public void deleteById(Integer id) {
        throw new TipException("暂时不支持删除用户详情");
//        delete().from(UserDetail.class)
//                .where(UserDetail::getUserId).eq(id)
//                .execute();
    }

    @Override
    public Page<UserDetail> list() {
        throw new TipException("暂时不支持用户详情列表查询");
//        return null;
    }

    @Override
    public UserDetail findByUserId(Integer userId) {
        return select().from(UserDetail.class)
                .where(UserDetail::getUserId).eq(userId)
                .one();
    }
}
