package com.group2.eshopbe.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ResponseObject {
    public static final String SUCCESS = "success";
    public static final String FAIL ="FAIL";
    private String status;
    private String message;
    private Object data;
    public ResponseObject(String status, String message, Object data){
        this.status=status;
        this.message=message;
        this.data=data;
    }
}
