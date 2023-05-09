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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

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
        if (req.getRequestURI().contains("/admin/")) {

            try {
                if (jwtTokenService.verifyToken(req.getHeader("Authorization"))) {
                    filterChain.doFilter(request, response);
                    return;
                }
                resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                resp.getWriter().write("Unauthorized");
                return;
            } catch (Exception e) {
                resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                resp.getWriter().write("Unauthorized");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}