package com.example.springdemo.Interceptors;

import com.example.springdemo.Services.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
    private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

//    @Autowired
    UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI()
//                + getParameters(request)
        );
        if(userInfo!=null && userInfo.getAccount()!=null){
            log.info("*******************"+userInfo.getAccount().toString()+"*************");
        }
        if(userInfo==null){
            log.info("******** NULL ***********");
        }
        return true;
//        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {

        log.info("[postHandle][" + request + "]");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response,Object handler, Exception ex)
            throws Exception {
        if (ex != null){
            ex.printStackTrace();
        }
        log.info("[afterCompletion][" + request + "][exception: " + ex + "]");
    }

    public LoggerInterceptor(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}

