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
public class UserInfo implements BaseUserInfo {

    private Integer  userId;
    private String   username;
    private String   nickName;
    private UserType userType;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isCurrentUser(Integer userId) {

        return userId != null && userId.equals(this.userId);
    }

}
