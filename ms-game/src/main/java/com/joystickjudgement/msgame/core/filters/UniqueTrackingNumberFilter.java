package com.joystickjudgement.msgame.core.filters;

import com.joystickjudgement.msgame.core.exceptions.InternalServerErrorHttpException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.*;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Component
@Order(HIGHEST_PRECEDENCE)
@Log4j2
public class UniqueTrackingNumberFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) {

        log.info("[START] - Starting x-request-id filter");

        try {
            if (
                    request.getRequestURI().contains(ACTUATOR_PATH)
                            || request.getRequestURI().contains(SWAGGER_PATH)
                            || request.getRequestURI().contains(SWAGGER_CONFIG_PATH)
            ) {
                log.info("[END] - Request path: {} is not valid for this filter.", request.getRequestURI());
                filterChain.doFilter(request, response);
                return;
            }

            var requestId = request.getHeader(REQUEST_ID_HEADER);

            if (requestId == null || requestId.isBlank()) {
                log.error("[END] - {} value: {} is not valid for this filter.", REQUEST_ID_HEADER, requestId);
                throw new InternalServerErrorHttpException(REQUEST_ID_HEADER + " header is required");
            }

            log.info("[END] - Received x-request-id header with value: {}", requestId);

            MDC.put(REQUEST_ID_HEADER, requestId);

            filterChain.doFilter(request, response);

        } catch (final IOException | ServletException | IllegalArgumentException e) {
            log.error("[END] - Error while trying to filter request.", e);
            throw new InternalServerErrorHttpException(e.getMessage());
        } finally {
            MDC.clear();
        }

    }
}
