package com.example.siirecruitmenttask.exception;

public class PromotionalCodeAlreadyExistsException extends Exception{
    @Override
    public String getMessage() {
        return "Promotional code already exists!";
    }
}
