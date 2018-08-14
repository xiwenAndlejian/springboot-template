package com.shuda.config;

import io.github.biezhi.anima.Anima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * @author gx <br>
 * @date 2018-08-08 <br>
 */
@Configuration
public class DataBaseConfig {

    private Environment env;

    @Autowired
    public DataBaseConfig(Environment environment) {
        env = environment;
    }

    /**
     * 创建 anima bean， 类似于dataSource
     */
    @Bean
    public Anima anima() {
        String url      = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");
        assert !StringUtils.isEmpty(url);
        assert !StringUtils.isEmpty(username);
        assert !StringUtils.isEmpty(password);

        return Anima.open(url, username, password);
    }
}
