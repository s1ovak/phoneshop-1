package com.es.phoneshop.web.controller.pages.ajax;

public class AddToCartResponse {
    String errorMessage;

    public AddToCartResponse() {
    }

    public AddToCartResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
