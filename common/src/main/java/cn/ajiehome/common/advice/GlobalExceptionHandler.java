package cn.ajiehome.common.advice;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.exception.entity.bo.ResultBO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handler(Exception e, HttpServletResponse response){
        if(e instanceof ApplicationException){
            response.setStatus(202);
            return ResultBO.newResultBO((ApplicationException) e);
        }else {
            response.setStatus(203);
            return ResultBO.newResultBO(CodeType.SYSTEM_EXCEPTION,e.getMessage());
        }
    }
}
