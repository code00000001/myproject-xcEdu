package com.xuecheng.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xuecheng.framework.model.response.ResponseResult;
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
     * 捕获异常类
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException e){
        LOGGER.error("catch exception: {}",e.getMessage(),e);
        return new ResponseResult(e.getResultCode());
    }
}
