package com.dekuofa;

import com.dekuofa.model.entity.Permission;
import com.dekuofa.model.entity.Role;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.core.Joins;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.dekuofa.utils.CommonKit.ids2String;
import static io.github.biezhi.anima.Anima.select;

/**
 * @author gx <br>
 * @date 2018-08-14 <br>
 */
@Slf4j
public class PermissionTest {

    @Before
    public void setUp() {
        Anima.open("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useSSL=false", "ganx", "123456");
    }

    @Test
    public void select_permissions() {
        List<Integer> roles = Arrays.asList(1, 2);
        String ids = ids2String(roles);
        List<Permission> permissions = select()
                .bySQL(Permission.class, "select p.id, p.permission  from role_permission rp\n" +
                "left join permission p\n" +
                "    on rp.permission_id = p.id\n" +
                "where rp.role_id in (?)", ids).all();
        permissions.forEach(x -> log.info(x.getPermission()));
    }

    @Test
    public void test() {
        isHappy(19);
    }

    public static boolean isHappy(int n) {
        Set<Integer> nums = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n != 0) {
                int num = n % 10;
                sum += num * num;
                n /= 10;
            }
            log.info("sum = " + sum);
            boolean flag = nums.add(sum);
            if (!flag) {
                return false;
            }
            n = sum;
        }
        return true;
    }

    @Test
    public void test_1() {
        System.out.println(removeDuplicateLetters("bcabc"));
    }
    public static String removeDuplicateLetters(String s) {
        char[] result = new char[26];
        char[] chars  = s.toCharArray();
        for (int i = 0; i < s.length(); i ++) {
            result[chars[i] - 'a'] = 1;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            if (result[i] == 1) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.toString();
    }

    @Test
    public void test_2() {
    }

}
