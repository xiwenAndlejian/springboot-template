package com.dekuofa.service;

import com.dekuofa.model.entity.UserDetail;

/**
 * @author dekuofa <br>
 * @date 2018-11-07 <br>
 */
public interface UserDetailService extends BaseService<UserDetail, Integer> {

    UserDetail findByUserId(Integer userId);
}
