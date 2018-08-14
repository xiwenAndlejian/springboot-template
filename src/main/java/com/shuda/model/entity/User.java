package com.shuda.model.entity;

import io.github.biezhi.anima.Model;
import lombok.Data;

/**
 * @author gx <br>
 * @date 2018-08-08 <br>
 */
@Data
public class User extends Model {

    private Integer id;
    private String  userName;
    private Integer age;

    public User() {
    }

    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

}
