package com.kz.practice.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 15:17  15:17
 */
public class Response<T> implements Serializable {
    protected Boolean status;

    protected String code = ResponseConstant.SUCCESS.getCode();

    protected String msg = ResponseConstant.SUCCESS.getShowMsg();

    protected String errorMsg = ResponseConstant.SUCCESS.getErrorMsg();

    protected T data;

    protected String timestamp;

    public Response(Boolean status, String code, String msg, String errorMsg, T data, String timestamp) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
        this.data = data;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    /**
     * @param status
     * @param code
     * @param msg
     * @param errorMsg
     * @exception
     */
    public Response(Boolean status, String code, String msg, String errorMsg) {
        super();
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }

    /**
     * @param data
     * @exception
     */
    public Response(T data) {
        super();
        this.data = data;
    }

    /**
     * @param status
     * @param code
     * @param msg
     * @param errorMsg
     * @param data
     * @exception
     */
    public Response(Boolean status, String code, String msg, String errorMsg, T data) {
        super();
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    /**
     *
     * @exception
     */
    public Response() {
        super();
    }

    /**
     * 调用结果
     * @return boolean
     */
    public Boolean success(){
        return status;
    }

    /**
     * 错误编码
     * @return
     */
    public String getCode(){
        return code;
    }

    /**
     * 业务提示信息
     * @return
     */
    public String getMsg(){
        return msg;
    }

    /**
     * 错误信息
     * @return
     */
    public String getErrorMsg(){
        return errorMsg;
    }

    /**
     * 实体数据
     * @return
     */
    public T getData(){
        return data;
    }


}
