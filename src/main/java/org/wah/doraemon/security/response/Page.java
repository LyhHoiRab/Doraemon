package org.wah.doraemon.security.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果
 */
@Data
public class Page<T>{

    //总条数
    private Long total;
    //分页参数
    private PageRequest pageRequest;
    //分页数据
    private List<T> content = new ArrayList<T>();
    //是否有下一页
    private Boolean hasNext;

    public Page(List<T> content, PageRequest pageRequest, Long total){
        this.pageRequest = pageRequest;
        this.content.addAll(content);
        this.total = (!content.isEmpty() && pageRequest != null && (pageRequest.getOffset() + pageRequest.getPageSize()) > total) ? pageRequest.getOffset() + content.size() : total;
        this.hasNext = (pageRequest.getOffset() + content.size() < this.total);
    }
}
