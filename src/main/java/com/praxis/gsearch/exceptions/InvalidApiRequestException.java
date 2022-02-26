package com.praxis.gsearch.exceptions;

import com.praxis.gsearch.model.ApiRequestError;

public class InvalidApiRequestException extends Exception {
    public ApiRequestError apiRequestError;

    public InvalidApiRequestException(ApiRequestError _apiRequestError){
        super(_apiRequestError.getError().getError());
        this.apiRequestError = _apiRequestError;
    }
}