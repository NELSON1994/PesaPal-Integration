package com.pesapal.integration.pesapal.wrapper;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GenericResponseWrapper<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String status;
    private String message;
    private Date date=new Date();
    private T data;


}
