package com.dekuofa.utils;

import com.dekuofa.model.enums.BaseCodeEnum;
import org.sql2o.converters.Converter;
import org.sql2o.converters.ConverterException;

/**
 * @author dekuofa <br>
 * @date 2018-09-16 <br>
 */
public class BaseCodeEnumConverterFactory {

    public static <E extends Enum & BaseCodeEnum> Converter<E> newConverter(final Class<E> enumType) {

        return new Converter<E>() {
            @SuppressWarnings("unchecked")
            @Override
            public E convert(Object val) throws ConverterException {
                if (val == null) {
                    return null;
                }
                try {
                    E[] enums = enumType.getEnumConstants();
                    for (E e : enums) {
                        if (val.equals(e.getCode())) {
                            return e;
                        }
                    }
                } catch (Throwable t) {
                    throw new ConverterException("Error converting value '" + val.toString() + "' to " + enumType.getName(), t);
                }
                throw new ConverterException("Cannot convert type '" + val.getClass().getName() + "' to an Enum");
            }

            @Override
            public Object toDatabaseParam(Enum val) {
                return val.name();
            }
        };
    }

    public static Converter<BaseCodeEnum> newBaseCodeConverter() {
        return new Converter<BaseCodeEnum>() {
            @Override
            public BaseCodeEnum convert(Object val) throws ConverterException {
                if (val == null) {
                    return null;
                }
                try {
                    BaseCodeEnum[] enums = BaseCodeEnum.class.getEnumConstants();
                    for (BaseCodeEnum e : enums) {
                        if (val.equals(e.getCode())) {
                            return e;
                        }
                    }
                } catch (Throwable t) {
                    throw new ConverterException("Error converting value '" + val.toString() + "' to " + BaseCodeEnum.class.getName(), t);
                }
                throw new ConverterException("Cannot convert type '" + val.getClass().getName() + "' to an Enum");
            }

            @Override
            public Object toDatabaseParam(BaseCodeEnum val) {
                return val.getCode();
            }
        };
    }



}
