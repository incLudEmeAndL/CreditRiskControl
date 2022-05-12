package com.chenwei.csust.util;

import com.chenwei.csust.common.constante.ResponseStatusConst;
import com.chenwei.csust.entity.ApiResult;

public class ApiResultHandler {
    public static ApiResult apiResult = new ApiResult();
    /**
     * 请求成功响应 有参
     * @param object
     * @return ApiResult
     */
    public static ApiResult success(Object object) {
        apiResult.setCode(ResponseStatusConst.StatusOk);
        apiResult.setData(object);
        apiResult.setMessage("success");
        return apiResult;
    }

    /**
     * 无参请求成功响应
     * @return ApiResult
     */
    public static ApiResult success() {return success(null);}

    /**
     * 自定义响应
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return ApiResult
     */
    public static <T> ApiResult buildApiResult(Integer code, String message, T data) {
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(message);
        return apiResult;
    }
}
