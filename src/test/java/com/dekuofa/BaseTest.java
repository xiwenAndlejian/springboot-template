package com.dekuofa;

import com.dekuofa.model.NormalUserInfo;
import com.dekuofa.model.UserInfo;
import com.dekuofa.model.enums.UserType;
import com.dekuofa.utils.DateUtil;

import static com.dekuofa.utils.DateUtil.newUnixMilliSecond;

/**
 * @author dekuofa <br>
 * @date 2018-11-07 <br>
 */
public class BaseTest {

    public UserInfo getUserInfo(){
        NormalUserInfo userInfo = new NormalUserInfo();
        userInfo.setNickName("测试");
        userInfo.setUserId(1);
        userInfo.setUsername("test");
        userInfo.setUserType(UserType.ADMIN);
        return userInfo;
    }

    public Long getTime(){
        return newUnixMilliSecond();
    }
}
