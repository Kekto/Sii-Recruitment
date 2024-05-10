package com.example.siirecruitmenttask.exception;

public class PromotionalCodeInvalidDataException extends Exception {
    @Override
    public String getMessage() {
        return "Promotional code data is invalid!";
    }
}
