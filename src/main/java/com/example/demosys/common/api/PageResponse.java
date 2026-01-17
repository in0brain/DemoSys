package com.example.demosys.common.api;

import lombok.Data;

import java.util.List;

/**
 * PageResponse
 * 通用分页返回对象
 */
@Data
public class PageResponse {

    /** 当前页数据 */
    private List<?> records;

    /** 总记录数 */
    private long total;

    /** 当前页号（从 1 开始） */
    private int page;

    /** 每页大小 */
    private int pageSize;

    public static PageResponse of(List<?> records, long total, int page, int pageSize) {
        PageResponse r = new PageResponse();
        r.records = records;
        r.total = total;
        r.page = page;
        r.pageSize = pageSize;
        return r;
    }
}
