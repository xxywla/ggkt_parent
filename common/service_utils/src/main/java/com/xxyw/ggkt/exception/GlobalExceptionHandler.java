package com.xxyw.ggkt.exception;

import com.xxyw.ggkt.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. 全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(null).message("执行了全局异常处理");
    }

    // 2. 特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail(null).message("执行了ArithmeticException异常处理");
    }

    // 3. 自定义异常处理
    @ExceptionHandler(GgktException.class)
    @ResponseBody
    public Result error(GgktException e) {
        e.printStackTrace();
        return Result.fail(null).message(e.getMsg()).code(e.getCode());
    }
}
