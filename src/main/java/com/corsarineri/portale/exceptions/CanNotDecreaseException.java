package com.corsarineri.portale.exceptions;

public class CanNotDecreaseException extends RuntimeException{
    public CanNotDecreaseException(){
        super("you can not decrease block value < 1");
    }
}
