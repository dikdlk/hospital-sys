package com.hy.pojo;

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

    public Result(int i, String 插入, Hospital hospital) {
    }

    public Result(int i, String 插入) {
    }

    public Result(String code, String 插入) {
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
