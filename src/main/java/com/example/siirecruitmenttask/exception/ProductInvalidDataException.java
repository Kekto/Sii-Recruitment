package com.example.siirecruitmenttask.exception;

public class ProductInvalidDataException extends Exception{
    @Override
    public String getMessage() {
        return "Product data is invalid!";
    }
}
