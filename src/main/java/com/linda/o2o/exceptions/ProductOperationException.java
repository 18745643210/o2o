package com.linda.o2o.exceptions;

public class ProductOperationException extends RuntimeException {
    private static final long serialVersionUID = 4653499762092071082L;

    public ProductOperationException(String msg){
        super(msg);
    }
}
