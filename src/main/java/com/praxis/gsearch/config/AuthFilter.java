package com.praxis.gsearch.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class AuthFilter extends AbstractPreAuthenticatedProcessingFilter {
    private String requestHeader;

    public AuthFilter(String _requestHeader) {
        this.requestHeader = _requestHeader;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(requestHeader);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "NA";
    }
}