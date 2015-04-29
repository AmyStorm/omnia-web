package com.omnia.authentication.controller;

import com.omnia.authentication.service.LoginService;
import com.omnia.authentication.vo.LoginSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by khaerothe on 2015/4/29.
 */
@Controller
public class LoginController {

    private LoginService loginService = new LoginService();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, HttpServletResponse response){
        LoginSession loginSession = (LoginSession) request.getSession().getAttribute("loginSession");
        if(loginSession != null){
            return "redirect:/index";
        }else{
            return "login/login";
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginProcess(HttpServletRequest request, HttpServletResponse response, String username, String password){
        LoginSession loginSession = loginService.login(username, password);
        if(loginSession != null){
            request.getSession().setAttribute("loginSession", loginSession);
            return "{\"success\" : true}";
        }else{
            return "{\"success\" : true}";
        }
    }
}
