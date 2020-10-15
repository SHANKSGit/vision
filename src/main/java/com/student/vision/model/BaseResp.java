package com.student.vision.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResp<T> {
    private int code;
    private String msg;
    private int total;
    private T body;

    public BaseResp(T body) {
        this.code=200;
        this.body = body;
    }

    public BaseResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResp() {
    }

    public static BaseResp fail(String msg){
        return new BaseResp(500,msg);
    }



}
