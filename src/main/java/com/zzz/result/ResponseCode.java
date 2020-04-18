package com.zzz.result;

public enum ResponseCode {

    // 公共请求信息
    SUCCESS(200, "请求成功"),
    TABLE_SUCCESS(0, "请求成功"),

    FAIL(500, "请求失败，请联系管理员"),

//    BAD_REQUEST(400, "错误的请求"),
//    UNAUTHORIZED(401,"未授权, 请登录"),
//    FORBIDDEN(403, "超出权限"),

//    BAD_DATE_FORMAT(4001, "日期格式错误"),
//    BAD_OPERATION(4002, "无法识别的操作"),
//    NULL_USERNAME(4011, "用户名不存在"),
//    WRONG_PASSWORD(4012, "密码错误"),
//    ANCHOR_DONT_EXIST(4013, "主播不存在"),

    UNASSORTED(-1, "未分类的错误"),
    ;
    private Integer status;

    private String message;

    ResponseCode(Integer code, String message) {
        this.status = code;
        this.message = message;
    }

    public Integer getCode() {
        return status;
    }

    public void setCode(Integer code) {
        this.status = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String name) {
        for (ResponseCode item : ResponseCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
        for (ResponseCode item : ResponseCode.values()) {
            if (item.name().equals(name)) {
                return item.status;
            }
        }
        return null;
    }
}
