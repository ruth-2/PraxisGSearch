package com.praxis.gsearch.model;

public class HttpError {
    private String error;

    public HttpError(String _error) {
        this.error = _error;
    }

    public String getError() {
        return this.error;
    }
}