package com.yearly.idol.api.yearly_idol.Common.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("\n@@@@ Request Start @@@@\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // getReader 쓰면 에러나니까 컨텐츠를 미리 캐싱하고 filterChain.doFilter 가 끝난 뒤 로그로 찍기
        var cachedReq = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var cachedRes = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(cachedReq, cachedRes);

        var reqJson = new String(cachedReq.getContentAsByteArray());
        log.info("req: {}", reqJson);
        var resJson = new String(cachedRes.getContentAsByteArray());
        log.info("res: {}", resJson);

        // res.getContentAsByteArray 로 한번 res를 읽었기 때문에 이것도 다시 복사 안해주면 빈 response가 내려감
        cachedRes.copyBodyToResponse();

        log.info("\n@@@@ Request Done @@@@\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}
