package com.dekuofa.service;

import io.github.biezhi.anima.page.Page;

/**
 * @author dekuofa <br>
 * @date 2018-09-04 <br>
 */
public interface BaseService<T> {
    Integer save(T t);

    void modify(T t);

    void delete(T t);

    Page<T> list();
}
