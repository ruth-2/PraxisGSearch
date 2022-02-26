package com.praxis.gsearch.model;

public class ApiRequestError {
    private int code;
    private HttpError httpError;

    public ApiRequestError(int _code, String message) {
        this.code = _code;
        this.httpError = new HttpError(message);
    }

    public int getCode() {
        return this.code;
    }

    public HttpError getError() {
        return this.httpError;
    }
}