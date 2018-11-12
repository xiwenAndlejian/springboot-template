package com.dekuofa.model.enums;

import com.dekuofa.config.DataBaseConfig;

/**
 * 新增枚举后，除了实现此接口，还需要在 {@link DataBaseConfig#converters()} 中增加对应 Convert 对象
 *
 * @author ganxiang <br>
 * 时间：2018年05月21日 17:56<br>
 * 标题：BaseCodeEnum<br>
 * 功能：<br>
 */
public interface BaseCodeEnum {
    /**
     * 获取枚举类型的编码，用于存储在数据库中
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取枚举类型的文字描述，用于界面展示
     *
     * @return
     */
    String getDesc();

}
