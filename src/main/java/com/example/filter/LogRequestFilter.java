package com.example.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;

@WebFilter(urlPatterns = { "/hello" },
           filterName = "LogRequestFilter",
           description = "Applied to all requests")
public class LogRequestFilter implements Filter {

    private static final String X_CORRELATION_ID = "X-Correlation-Id";
    private static final String LOG_CORRELATION_ID = "logCorrelationId";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        setMdc(((HttpServletRequest) request).getHeader(X_CORRELATION_ID));
        filterChain.doFilter(request, response);
    }

    private void setMdc(String correlationId) {
        MDC.put(LOG_CORRELATION_ID, correlationId == null ? UUID.randomUUID() : correlationId);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
