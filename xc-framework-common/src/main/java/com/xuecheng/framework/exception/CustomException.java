package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 自定义错误异常类
 * @author code
 */
public class CustomException extends RuntimeException {
    /**
     * 错误码
     */
    private ResultCode resultCode;

    /**
     * 有参构造
     * @param resultCode
     */
    public CustomException(ResultCode resultCode){
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }
}
