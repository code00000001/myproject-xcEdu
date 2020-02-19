package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 异常捕获类
 * @author code
 */
@ControllerAdvice
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);


    /**
     * 使用ImmutableMap存放异常类型和错误代码的映射，特点是创建后不可改变且线程安全
     */
    private ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;

    /**
     * 使用builder来构建异常类型和错误代码的异常
     */
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    /**
     * 在这里给ImmutableMap加入一些基础的异常代码
     */
    static {
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }

    /**
     * 捕获自定义异常类
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e){
        LOGGER.error("catch exception: {}",e.getMessage(),e);
        return new ResponseResult(e.getResultCode());
    }

    /**
     * 捕获不可知异常类
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e){
        LOGGER.error("catch exception: {}",e);
        if (EXCEPTIONS == null){
            //此时ImmutableMap就构建成功
            EXCEPTIONS = builder.build();
        }
        //从EXCEPTION里查找错误代码
        ResultCode resultCode = EXCEPTIONS.get(e.getClass());
        if (resultCode != null){
            return new ResponseResult(resultCode);
        }
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }
}
