package com.example.userlogindemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页请求
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    //当前页码
    private int pageNum;

    //每页数量
    private int pageSize;
}
