package com.linda.o2o.exceptions;

public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID = 5812853613610624443L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }

}
