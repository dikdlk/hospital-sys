package com.gjj.exception;

import com.gjj.pojo.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author zh
 * @Description 全局异常处理器，统一处理400等错误响应
 * @createTime 2026/7/5
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求体不可读（如JSON格式错误）
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return Result.error("请求参数格式错误，请检查JSON数据");
    }

    /**
     * 缺少必填的请求参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingParam(MissingServletRequestParameterException e) {
        return Result.error("缺少必填参数: " + e.getParameterName());
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        return Result.error("参数类型错误: " + e.getName() + " 应为 "
                + (e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "正确类型"));
    }

    /**
     * 非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgument(IllegalArgumentException e) {
        return Result.error("参数不合法: " + e.getMessage());
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointer(NullPointerException e) {
        return Result.error("数据不存在或已被删除");
    }

    /**
     * 其他运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        return Result.error("服务器内部错误: " + e.getMessage());
    }

    /**
     * 兜底：捕获所有未处理的异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.error("服务器异常，请联系管理员");
    }
}
