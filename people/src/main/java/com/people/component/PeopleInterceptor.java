package com.people.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PeopleInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(PeopleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        printLog(request, "PRE HANDLE");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        printLog(request, "AFTER COMPLETION");
    }

    private void printLog(HttpServletRequest request, String prefix){
        try {
            logger.info("### "+prefix+" ### REQUEST URL: {}", request.getRequestURI());
        } catch (Exception e) {
            logger.error("### "+prefix+" ### ERROR MESSAGE: "+e.getMessage());
        }
    }
}
