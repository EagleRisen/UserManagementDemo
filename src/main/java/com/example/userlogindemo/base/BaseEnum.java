package com.example.userlogindemo.base;

/**
 * @ClassName BaseEnum
 * @Description TODO
 * @Author nsxconquer
 * @Date 2021/1/28 8:30 PM
 * @Version 1.0
 */
public interface BaseEnum<K, V> {
    //获取编码
    K code();

    /**
     * 获取描述
     * @return
     */
    V desc();
}
