package com.dekuofa.config;

import com.dekuofa.model.enums.BaseStatus;
import com.dekuofa.utils.BaseCodeEnumConverterFactory;
import io.github.biezhi.anima.Anima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.sql2o.converters.Converter;
import org.sql2o.converters.java8.LocalDateConverter;
import org.sql2o.converters.java8.LocalDateTimeConverter;
import org.sql2o.quirks.NoQuirks;
import org.sql2o.quirks.Quirks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dekuofa <br>
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
    public Anima anima(Quirks quirks) {
        String url      = env.getProperty("spring.datasource.url");
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");
        assert !StringUtils.isEmpty(url);
        assert !StringUtils.isEmpty(username);
        assert !StringUtils.isEmpty(password);
        return Anima.open(url, username, password, quirks);
    }

    @Bean
    public Quirks quirks() {
        Map<Class<?>, Converter<?>> convertMap = new HashMap<>();
        convertMap.put(LocalDate.class, new LocalDateConverter());
        convertMap.put(LocalDateTime.class, new LocalDateTimeConverter());
        convertMap.put(BaseStatus.class, BaseCodeEnumConverterFactory.newConverter(BaseStatus.class));
        return new NoQuirks(convertMap);
    }
}
