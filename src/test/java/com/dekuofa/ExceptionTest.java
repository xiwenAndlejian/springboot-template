package com.dekuofa;


import com.dekuofa.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author dekuofa <br>
 * @date 2018-08-17 <br>
 */
public class ExceptionTest {

    @Test
    public void jsonIgnoreSerialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setPassword("123123");
        String serialized = objectMapper.writeValueAsString(user);

        System.out.println(serialized);
    }

    public static void main(String[] args) {
        try {
            a();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
        }
    }

    public static void a() throws Exception {
        b();
    }

    public static void b() throws Exception {
        throw new Exception("exception");
    }
}
