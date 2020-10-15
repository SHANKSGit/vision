package com.student.vision.config;

import com.student.vision.model.BaseResp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
 public class CustomExtHandler {

     //捕获全局异常,处理所有不可知的异常
     @ExceptionHandler(value=Exception.class)
     Object handleException(Exception e, HttpServletRequest request){
         BaseResp baseResp= BaseResp.fail(e.getMessage());
         System.out.println(e);
         return baseResp;
     }

 }