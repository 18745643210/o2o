package com.linda.o2o.exceptions;

public class ShopOperationException extends  RuntimeException {


    private static final long serialVersionUID = 4166762061997607636L;

    public ShopOperationException(String msg){
        super(msg);
    }
}
