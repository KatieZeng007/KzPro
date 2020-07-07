package com.kz.practice.util;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MissingServletRequestParameterException;
/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 15:15  15:15
 */
public class ResponseUtils {

    public static <T> Response<T> returnSuccess() {
        return new Response<>(true, ResponseConstant.SUCCESS.getCode(), ResponseConstant.SUCCESS.getShowMsg(), "", null);
    }

    public static <T> Response<T> returnSuccess(T data) {
        return new Response<>(true,
                ResponseConstant.SUCCESS.getCode(),
                ResponseConstant.SUCCESS.getShowMsg(),
                "", data);
    }

}
