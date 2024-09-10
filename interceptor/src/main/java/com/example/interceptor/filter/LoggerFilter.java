package com.example.interceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;


@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      
        log.info("LoggerFilter Start\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        /*
         2. 이렇게 buffer 출력하면 되는데, 미리 Reader 로 읽어버리면 controller 에 매핑을 못해줘서 에러 발생함
            var req = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
            var res = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
            var bufferedReader =  req.getReader();
            var bufferList = bufferedReader.lines().toList();
            bufferList.forEach(it -> {
            log.info("buffer: {}", it);
         });
        */

        // 3. getReader 쓰면 에러나니까 컨텐츠를 미리 캐싱하고 filterChain.doFilter 가 끝난 뒤 로그로 찍기
        var req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);


        filterChain.doFilter(req, res); // 서블랫 req, res 커스터마이징 하기
        // filterChain.doFilter(servletRequest, servletResponse); // 1. 이게 원본

        var reqJson = new String(req.getContentAsByteArray());
        log.info("req: {}", reqJson);
        var resJson = new String(res.getContentAsByteArray()); 
        log.info("res: {}", resJson);

        log.info("LoggerFilter Done\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        
        res.copyBodyToResponse(); // res.getContentAsByteArray 로 한번 res를 읽었기 때문에 이것도 다시 복사 안해주면 빈 response가 내려감
    }
}
