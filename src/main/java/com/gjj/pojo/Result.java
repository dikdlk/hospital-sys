package com.gjj.pojo;

/**
 * @author zh
 * @Description TODO
 * @createTime 2026/5/28
 */
public class Result {
    private String code;  //200   1001
    private String message;  //响应成功
    private Object data;  //用户存储给前端的数据


    public Result() {
    }

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应（code=200）
     */
    public static Result success(String message, Object data) {
        return new Result("200", message, data);
    }

    /**
     * 成功响应（code=200），无数据
     */
    public static Result success(String message) {
        return new Result("200", message, null);
    }

    /**
     * 失败响应（code=400），请求参数有误
     */
    public static Result error(String message) {
        return new Result("400", message, null);
    }

    /**
     * 失败响应（code=400），请求参数有误，带数据
     */
    public static Result error(String message, Object data) {
        return new Result("400", message, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
