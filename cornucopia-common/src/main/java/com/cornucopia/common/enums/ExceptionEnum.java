package com.cornucopia.common.enums;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-08-14
 */
public enum ExceptionEnum {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空!"),
    CATEGORY_NOT_FOUND(404,"商品分类没有查到"),
    BRAND_NOT_FOUND(404,"品牌没有找到"),
    SPEC_GROUP_NOT_FIND(404,"商品规格组没有找到"),
    SPEC_PARAM_NOT_FIND(404,"商品规格参数没有找到"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    GOODS_NOT_FOND(404,"商品没有找到"),
    GOODS_DETAIL_NOT_FOND(404,"商品详情不存在!"),
    GOODS_SKU_NOT_FOND(404,"商品SKU不存在!"),
    GOODS_STOCK_NOT_FOND(404,"商品STOCK不存在!"),
    CATEGORY_BRAND_SAVE_ERROR(500,"新增品牌中间表失败"),
    UPLOAD_FILE_ERROR(500,"上传文件失败"),
    INVALID_FILE_TYPE(400,"不匹配的文件类型"),
    GOODS_SAVE_ERROR(500,"新增商品失败");

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
