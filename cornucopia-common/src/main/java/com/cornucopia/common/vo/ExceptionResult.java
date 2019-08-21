package com.cornucopia.common.vo;

import com.cornucopia.common.enums.ExceptionEnum;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-08-14
 */
public class ExceptionResult {

    private int status;
    private String message;
    private Long timeStamp;


    public ExceptionResult(ExceptionEnum em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timeStamp = System.currentTimeMillis();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
