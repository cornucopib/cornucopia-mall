package com.cornucopia.common.enums;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-08-14
 */
public enum ExceptionEnum {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空!"),
    CATEGORY_NOT_FOUND(404,"商品分类没有查到");

    private int code;
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
