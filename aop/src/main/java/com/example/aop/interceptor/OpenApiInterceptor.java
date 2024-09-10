package com.example.aop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 인증, 인가를 보통 여기서 처리
 */
@Slf4j
@Component
public class OpenApiInterceptor implements HandlerInterceptor {

    /**
     * \@OpenApi annotation 이 있는지 체크하는 interceptor
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("@PRE HANDLER@");

        var handlerMethod = (HandlerMethod)handler;


        var methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
        if(methodLevel != null) {
            log.info("methodLevel true");
            return true;
        }

        var classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
        if(classLevel != null) {
            log.info("classLevel true");
            return true;
        }

        log.info("open api가 아닙니다.");
        return false;

//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("@POST HANDLER@");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("@AFTER COMPLETION@");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
