package com.dekuofa;

import com.dekuofa.model.entity.User;
import com.dekuofa.utils.DateUtil;
import io.github.biezhi.anima.Anima;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


/**
 * @author dekuofa <br>
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
        User user = new User();
        user.setUsername("test");
        user.setId(1);
        user.update();
//        user.setUsername("modify");
//        user.updateById(1);
//        List<Integer> roles = Arrays.asList(1, 2);
//        String ids = ids2String(roles);
//        List<Permission> permissions = select()
//                .bySQL(Permission.class, "select p.id, p.permission  from role_permission rp\n" +
//                "left join permission p\n" +
//                "    on rp.permission_id = p.id\n" +
//                "where rp.role_id in (?)", ids).all();
//        permissions.forEach(x -> log.info(x.getName()));
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
        System.out.println(DateUtil.newUnixMilliSecond());
    }

}
