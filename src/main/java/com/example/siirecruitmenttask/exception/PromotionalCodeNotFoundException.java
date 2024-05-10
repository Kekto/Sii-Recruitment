package com.example.siirecruitmenttask.exception;

public class PromotionalCodeNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Promotional code not found!";
    }
}
