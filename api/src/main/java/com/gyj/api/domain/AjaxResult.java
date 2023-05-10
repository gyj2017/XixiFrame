package com.gyj.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AjaxResult<T> {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp;


    public AjaxResult(Integer code, String msg, T data, LocalDateTime timestamp) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = timestamp;
    }

    public AjaxResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public AjaxResult() {
    }


    public static AjaxResult<Void> success() {
        return success("请求成功");
    }

    public static <T> AjaxResult<T> success(T data) {
        return success("请求成功", data);
    }

    public static AjaxResult<Void> success(String msg) {
        return success(msg, null);
    }

    public static <T> AjaxResult<T> success(String msg, T data) {
        return new AjaxResult(200, msg, data);
    }

    public static AjaxResult<Void> error() {
        return error("请求失败");
    }

    public static AjaxResult<Void> error(String msg) {
        return error((String)msg, null);
    }

    public static <T> AjaxResult<T> error(String msg, T data) {
        return new AjaxResult(500, msg, data);
    }

    public static AjaxResult<Void> error(Integer code, String msg) {
        return new AjaxResult(code, msg, (Object)null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public AjaxResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public AjaxResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public AjaxResult<T> setData(T data) {
        this.data = data;
        return this;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public AjaxResult<T> setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
