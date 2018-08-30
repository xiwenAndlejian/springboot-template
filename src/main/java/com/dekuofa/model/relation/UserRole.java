package com.dekuofa.model.relation;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author dekuofa <br>
 * @date 2018-08-28 <br>
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends Model {
    private Integer userId;
    private Integer roleId;

}
