package com.example.userlogindemo.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO
 * @Author nsxconquer
 * @Date 2021/1/28 7:53 PM
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private boolean success = true;

    //状态码
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;

    //编码
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    //相关消息
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    //相关数据
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, Integer status) {
        this.success = success;
        this.status = status;
    }

    public Result(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, Integer status, String code, String msg) {
        this.success = success;
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, String code, String msg, Object data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
