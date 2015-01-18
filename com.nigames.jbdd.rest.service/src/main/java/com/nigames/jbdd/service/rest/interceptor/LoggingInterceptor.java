package com.nigames.jbdd.service.rest.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Request;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Provider
class LoggingInterceptor implements ContainerRequestFilter {

    private static final Logger LOG = LoggerFactory
            .getLogger(LoggingInterceptor.class);

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        logMethodAndUri(requestContext);
        logAllCookies(requestContext);
        logAllHeaders(requestContext);
    }

    private void logMethodAndUri(final ContainerRequestContext requestContext) {
        final Request request = requestContext.getRequest();
        LOG.info("{} {}", request.getMethod(), requestContext.getUriInfo().getRequestUri());
    }

    private void logAllCookies(final ContainerRequestContext requestContext) {
        for (final Map.Entry<String, Cookie> cookie : requestContext.getCookies().entrySet()) {
            LOG.info("COOKIE: {} - {}", cookie.getValue().getName(), cookie.getValue().getValue());
        }
    }

    private void logAllHeaders(final ContainerRequestContext requestContext) {
        requestContext.getHeaders().entrySet().forEach(this::logSingleHeader);
    }

    private void logSingleHeader(final Map.Entry<String, List<String>> header) {
        for (final String val : header.getValue()) {
            LOG.info("HEADER: {} - {}", header.getKey(), val);
        }
    }
}
