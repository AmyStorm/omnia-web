package com.omnia.user.interceptor;

import com.omnia.authentication.vo.LoginSession;
import com.omnia.user.annotation.Login;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;

/**
 * Created by khaerothe on 2015/4/5.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final Log LOG = LogFactory.getLog(AuthenticationInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("Login Authentication, path: " + request.getRequestURI());
        if(handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            Annotation loginAnnotation = method.getMethodAnnotation(Login.class);
            if(null == loginAnnotation){
                return super.preHandle(request, response, handler);
            }
            HttpSession session = request.getSession();
            LoginSession user = (LoginSession) session.getAttribute("loginSession");
            if(user != null){
                // login success
                return super.preHandle(request, response, handler);
            }else{
                // no login
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }else{
            //no spring mvc controller handler
            return false;
        }

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
