package com.dekuofa.model;

import com.dekuofa.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dekuofa <br>
 * @date 2018-08-21 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NormalUserInfo implements UserInfo {

    private Integer  userId;
    private String   username;
    private String   nickName;
    private UserType userType;

    @Override
    public boolean isEmpty() {

        return userId == null || username == null || nickName == null;
    }

    @Override
    public boolean isCurrentUser(Integer userId) {

        return userId != null && userId.equals(this.userId);
    }

}
