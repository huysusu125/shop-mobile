package com.project.shopmobile.filter;

import com.project.shopmobile.service.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(-1)
@Slf4j
public class AuthFilter implements Filter {
    private final JwtTokenService jwtTokenService;

    public AuthFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        log.info("Request to URI :{}", req.getRequestURI());
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
            resp.setHeader("Access-Control-Max-Age", "3600");
            resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        if (req.getRequestURI().contains("/admin/")) {

            try {
                if (!jwtTokenService.verifyToken(req.getHeader("Authorization"))) {
                    resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                    resp.getWriter().write("Unauthorized");
                    return;
                }
            } catch (Exception e) {
                resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                resp.getWriter().write("Unauthorized");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}