package com.bkhb.EchoSphere.dto;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bkhb.EchoSphere.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {

    /**
     * 当前页数
     */
    private Long current;

    /**
     * 每页条数
     */
    private Long pageSize;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据
     */
    private List<T> records;

    /**
     * 总页数
     */
    private Long totalPage;

    public PageDto(IPage<T> page) {
        this.current = page.getCurrent();
        this.pageSize = page.getSize();
        this.total = page.getTotal();
        this.records = page.getRecords();
        this.totalPage = page.getPages();
    }
}
