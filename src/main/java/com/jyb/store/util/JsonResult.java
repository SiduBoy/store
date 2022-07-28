package com.jyb.store.util;

import java.io.Serializable;

/**
 * 状态信息
 * 状态码，状态描述信息，数据这部分封装在一个类中，将这类作为方法返回值，返回给前端浏览器。
 * Json格式的数据进行响应
 */
public class JsonResult<E> implements Serializable {
    /*状态码*/
    private Integer state;
    /*描述信息*/
    private String message;
    /* 数据 表示任意的数据类型 */
    private E date;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    /**
     * 将异常传递给构造方法
     * @param e
     */
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E date) {
        this.state = state;
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getDate() {
        return date;
    }

    public void setDate(E date) {
        this.date = date;
    }
}
