package com.dekuofa.model.entity;

import com.dekuofa.model.BaseEntity;
import com.dekuofa.model.enums.Gender;
import com.dekuofa.model.param.UserDetailParam;
import com.dekuofa.model.param.UserParam;
import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author dekuofa <br>
 * @date 2018-10-23 <br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_user_detail", pk = "user_id")
@NoArgsConstructor
public class UserDetail extends Model implements BaseEntity {

    private Integer   userId;
    // 性别
    private Gender    gender;
    // 真实姓名
    private String    realName;
    // 生日
    private LocalDate birthday;
    // 手机号
    private String    mobile;
    // 电子邮箱
    private String    email;

    private Long createTime;
    private Long modifyTime;

    public static UserDetail build(UserDetailParam param) {
        UserDetail detail = new UserDetail();

        detail.gender = param.getGender();
        detail.birthday = param.getBirthday();
        detail.email = param.getEmail();
        detail.mobile = param.getMobile();
        return detail;
    }

}
