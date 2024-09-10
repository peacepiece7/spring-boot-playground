package com.example.aop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;


@Slf4j
// @Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      
        log.info("LoggerFilter Start\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        var req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(req, res); // 서블랫 req, res 커스터마이징 하기

        var reqJson = new String(req.getContentAsByteArray());
        log.info("req: {}", reqJson);

        var resJson = new String(res.getContentAsByteArray());
        log.info("res: {}", resJson);

        res.copyBodyToResponse();

        log.info("LoggerFilter Done\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
