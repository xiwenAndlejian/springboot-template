package com.dekuofa.model.param;

import com.dekuofa.constant.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dekuofa <br>
 * @date 2018-08-22 <br>
 */
@Data
@NoArgsConstructor
public class PageParam {
    private int page  = Constants.DEFAULT_PAGE_NUM;
    private int limit = Constants.DEFAULT_PAGE_SIZE;

    public int getLimit() {
        // 限制最大页数
        if (page > Constants.DEFAULT_MAX_PAGE_SIZE) {
            this.page = Constants.DEFAULT_MAX_PAGE_SIZE;
        }
        return page;
    }

    public PageParam page(int page) {
        this.page = page;
        return this;
    }

    public PageParam limit(int limit) {
        this.limit = limit;
        return this;
    }

}
