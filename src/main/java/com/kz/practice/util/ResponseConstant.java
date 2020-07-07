package com.kz.practice.util;

import java.util.Date;

public enum ResponseConstant {

    /**
     * 访问成功，为全局预知内的有效反馈（捕获了预知的异常可返回100，传参数少传不能返回)
     */
    SUCCESS("100", "success", "success"),
    SYS_EXCEPTION("102", "系统异常", "网络开小差了，请稍候再试！"),
    NEED_TOKEN("105", "用户未登录或者被挤掉了", "请重新登录"),
    TOKEN_EXPIRE("106", "短期token过期", "请重新登录"),
    PARAM_MISSING("110", "参数缺失", "%s不存在"),
    EXCEPTION("111", "未知错误", "网络开小差了，请稍后再试"),
    TOKEN_INVALID("122", "无效token", "请重新登录"),
    NO_TOKEN("123", "token被后清掉了", "请重新登录"),
    VALIDATE_EXCEPTION("2000", "数据验证失败！", "网络开小差了，请稍候再试！"),
    LOCK_EXCEPTION("3000", "分布式锁异常", "网络开小差了，请稍候再试！"),
    BUSY_EXCEPTION("4000", "业务逻辑异常", "网络开小差了，请稍候再试！"),
    INVALID_REQUEST("0", "无效的请求", "请确认该请求是否正确");

    private String code;
    private String showMsg;
    private String errorMsg;
    private String timestamp;

    /**
     * @throws
     */
    ResponseConstant(String code, String showMsg, String errorMsg) {
        this.code = code;
        this.showMsg = showMsg;
        this.errorMsg = errorMsg;
        this.timestamp = new Date().toString();
    }

    /**
     * @return code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return errorMsg
     */
    public String getErrorMsg() {
        return this.errorMsg;
    }

    /**
     * @return the showmsg
     */
    public String getShowMsg() {
        return this.showMsg;
    }
}
