package com.fabhotels.reviewsystem.dto;

public class ResponseObj {

    boolean result;
    Object data;
    String errorMessage;

    public ResponseObj() {
    }

    public ResponseObj(boolean result, Object data, String errorMessage) {
        this.result = result;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
