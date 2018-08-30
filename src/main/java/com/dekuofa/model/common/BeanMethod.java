package com.dekuofa.model.common;

import lombok.Builder;
import lombok.Data;

import java.util.function.Function;

/**
 * @author dekuofa <br>
 * @date 2018-08-21 <br>
 */
@Data
@Builder
public class BeanMethod<T, R> {

    private Class<?>       clazz;
    private String         field;
    private Class<R>       propertyType;
    private Function<T, R> function;

    public String getMethod() {
        if (field == null || field.length() == 0) {
            return "";
        }
        StringBuilder sb    = new StringBuilder("set");
        char          first = field.charAt(0);
        if (first >= 'a' && first <= 'z') {
            first = (char) (first - 32);
        }
        sb.append(first).append(field.substring(1));
        return sb.toString();
    }
}
