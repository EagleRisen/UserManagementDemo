package com.example.userlogindemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页返回结果
 */
@Data
@NoArgsConstructor
public class PageResult {
    //当前页码
    private int pageNum;
    //每页数量
    private int pageSize;
    //记录总数
    private long totalSize;
    //页码数量
    private int totalPages;
    //数据内容
    private List<?> content;
}
