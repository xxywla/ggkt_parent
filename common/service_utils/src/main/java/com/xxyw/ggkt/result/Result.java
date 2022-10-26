package com.xxyw.ggkt.result;

import lombok.Data;

// 统一返回格式
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(20000);
        result.setMessage("成功");
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        result.setCode(20001);
        result.setMessage("失败");
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public Result<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

}
