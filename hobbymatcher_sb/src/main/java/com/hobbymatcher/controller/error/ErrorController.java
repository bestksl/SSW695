package com.hobbymatcher.controller.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorController {
    /**
     * 一般的参数绑定时候抛出的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResultBean handleBindException(BindException ex){
        log.error(   "参数校验异常",ex);
        List<String> defaultMsg = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResultBean(BlogContant.FAIL,BlogContant.CODE_9999,defaultMsg.get(0),null);
    }

    /**
     * 单个参数校验
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ResultBean handleBindGetException(ConstraintViolationException ex){
        log.error(   "单个参数校验异常",ex);
        List<String> defaultMsg = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return new ResultBean(BlogContant.FAIL,BlogContant.CODE_9999,defaultMsg.get(0),null);
    }

    /**
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultBean handleException(Exception ex){
        if(ex instanceof BlogRuntimeException){
            log.error( "抛出自定义异常",ex);
        }else {
            log.error( "系统内部异常",ex);
        }
        return new ResultBean(BlogContant.FAIL,BlogContant.CODE_9999,ex.getMessage(),null);
    }

}
