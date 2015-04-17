package com.omnia.authentication.interceptor;

import com.omnia.user.annotation.Login;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;

/**
 * Created by khaerothe on 2015/4/5.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle request invoke--------Login");
        System.out.println(handler.getClass().getName());
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            Annotation loginAnnotation = method.getMethodAnnotation(Login.class);
            if(null == loginAnnotation){
                return super.preHandle(request, response, handler);
            }
            HttpSession session = request.getSession();
            session.getAttribute("login");
        }else{
            //no spring mvc controller handler
            return false;
        }


//        Object session = request.getSession().getAttribute("login");
//        if(session == null){
//            return false;
//        }else{
        return super.preHandle(request, response, handler);
//        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
