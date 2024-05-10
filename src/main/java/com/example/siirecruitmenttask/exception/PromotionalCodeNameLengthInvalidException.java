package com.example.siirecruitmenttask.exception;

public class PromotionalCodeNameLengthInvalidException extends Exception {
    @Override
    public String getMessage() {
        return "Promotional code name should be between 3 and 24 letters!";
    }
}
