package com.dekuofa.utils;

import com.dekuofa.model.enums.BaseCodeEnum;
import com.dekuofa.model.enums.Gender;
import com.dekuofa.model.enums.UserType;
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

    public static <T extends BaseCodeEnum> Converter<T> newCodeConverter(Class<T> clazz) {
        return new Converter<T>() {
            @Override
            public T convert(Object val) throws ConverterException {
                if (val == null) {
                    return null;
                }
                try {
                    T[] enums = clazz.getEnumConstants();
                    for (T e : enums) {
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
            public Object toDatabaseParam(T val) {
                return val.getCode();
            }
        };
    }

    public static Converter<Gender> newGenderConvert() {
        return new Converter<Gender>() {
            @Override
            public Gender convert(Object val) throws ConverterException {
                if (val == null) {
                    return null;
                }
                try {
                    Gender[] enums = Gender.class.getEnumConstants();
                    for (Gender e : enums) {
                        if (val.equals(e.getCode())) {
                            return e;
                        }
                    }
                } catch (Throwable t) {
                    throw new ConverterException("Error converting value '" + val.toString() + "' to " + Gender.class.getName(), t);
                }
                throw new ConverterException("Cannot convert type '" + val.getClass().getName() + "' to an Enum");
            }

            @Override
            public Object toDatabaseParam(Gender val) {
                return val.getCode();
            }
        };
    }

    public static Converter<UserType> newUserTypeConvert() {
        return new Converter<UserType>() {
            @Override
            public UserType convert(Object val) throws ConverterException {
                if (val == null) {
                    return null;
                }
                try {
                    UserType[] enums = UserType.class.getEnumConstants();
                    for (UserType e : enums) {
                        if (val.equals(e.getCode())) {
                            return e;
                        }
                    }
                } catch (Throwable t) {
                    throw new ConverterException("Error converting value '" + val.toString() + "' to " + UserType.class.getName(), t);
                }
                throw new ConverterException("Cannot convert type '" + val.getClass().getName() + "' to an Enum");
            }

            @Override
            public Object toDatabaseParam(UserType val) {
                return val.getCode();
            }
        };
    }



}
