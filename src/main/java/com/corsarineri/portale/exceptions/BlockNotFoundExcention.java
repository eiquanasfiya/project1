package com.corsarineri.portale.exceptions;

public class BlockNotFoundExcention extends RuntimeException{
    public BlockNotFoundExcention(Long id){
        super("Block not found : "+id);
    }
}
