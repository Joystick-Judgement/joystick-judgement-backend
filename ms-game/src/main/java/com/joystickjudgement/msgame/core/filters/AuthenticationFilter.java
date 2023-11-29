package com.joystickjudgement.msgame.core.filters;

import com.joystickjudgement.msgame.core.exceptions.InternalServerErrorHttpException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.API_KEY_HEADER;
import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.GAME_RESOURCE_BASE_PATH;

@Component
@Log4j2
public class AuthenticationFilter extends OncePerRequestFilter {

    @Value("${api.key}")
    private String apiKey;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) {

        try {
            if (!request.getRequestURI().contains(GAME_RESOURCE_BASE_PATH)) {
                filterChain.doFilter(request, response);
                return;
            }

            log.info("[START] - Starting authentication filter");

            var apiKeyHeader = request.getHeader(API_KEY_HEADER);

            if (apiKeyHeader == null || !apiKeyHeader.equals(apiKey)) {
                log.error("[END] - {} value: {} is not valid for this filter.", API_KEY_HEADER, apiKeyHeader);
                throw new InternalServerErrorHttpException(API_KEY_HEADER + " header is invalid");
            }

            log.info("[END] - Received api key header with value: {}", apiKeyHeader);

            filterChain.doFilter(request, response);

        } catch (final IOException | ServletException | IllegalArgumentException e) {
            log.error("[END] - Error while trying to filter request.", e);
            throw new InternalServerErrorHttpException(e.getMessage());
        } finally {
            MDC.clear();
        }

    }
}
