package com.omnia.authentication.controller;

import com.omnia.authentication.domain.AuthenticationToken;
import com.omnia.authentication.domain.command.LoginCommand;
import com.omnia.authentication.vo.LoginSession;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.callbacks.FutureCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ExecutionException;

/**
 * Created by khaerothe on 2015/4/29.
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CommandBus commandBus;

//    private LoginService loginService = new LoginService();

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
    public String loginProcess(HttpSession session, LoginCommand loginCommand){

        FutureCallback<AuthenticationToken> callback = new FutureCallback<>();
        commandBus.dispatch(new GenericCommandMessage<>(loginCommand), callback);

        AuthenticationToken user = null;
        try {
            user = callback.get();
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("error call of login command", e);
        }
        if(user != null){
            session.setAttribute("loginSession", user);
            return "{\"success\" : true}";
        }else{
            return "{\"success\" : true}";
        }
    }
}
