package org.wah.doraemon.security.response;

import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest{

    //默认页码
    private final long DEFAULT_PAGE_NUMBER = 1;
    //默认每页记录数
    private final long DEFAULT_PAGE_SIZE = 20;
    //当前页码
    private Long pageNumber;
    //每页记录数
    private Long pageSize;
    //查询的始游标
    private Long offset;

    public PageRequest(Long pageNumber, Long pageSize){
        this.pageNumber = (pageNumber == null || pageNumber < 1) ? DEFAULT_PAGE_NUMBER : pageNumber;
        this.pageSize = (pageSize == null || pageSize < 0) ? DEFAULT_PAGE_SIZE : pageSize;
        this.offset = (this.pageNumber - 1) * this.pageSize;
    }
}
