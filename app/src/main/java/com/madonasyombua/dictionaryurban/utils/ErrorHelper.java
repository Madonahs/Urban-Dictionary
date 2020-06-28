package com.madonasyombua.dictionaryurban.utils;

import com.madonasyombua.dictionaryurban.api.ERROR_STATUS;

public class ErrorHelper extends Throwable{
    private ERROR_STATUS errorStatus;
    private String message;
    public ErrorHelper(ERROR_STATUS errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
    }

    public ERROR_STATUS getErrorStatus() {
        return errorStatus;
    }

    public ErrorHelper(ERROR_STATUS errorStatus) {
        this.errorStatus = errorStatus;
    }

    public ErrorHelper(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
