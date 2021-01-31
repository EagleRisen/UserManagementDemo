package com.example.userlogindemo.util;

import com.example.userlogindemo.entity.PageRequest;
import com.example.userlogindemo.entity.PageResult;
import com.github.pagehelper.PageInfo;

/**
 * 分页工具类
 */
public class PageUtils {
    public static PageResult getPageResult(PageInfo pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
