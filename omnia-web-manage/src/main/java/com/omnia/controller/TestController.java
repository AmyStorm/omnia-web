package com.omnia.controller;

import com.omnia.module.command.user.annotation.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by khaerothe on 2015/4/1.
 */
@Controller
public class TestController {

    @Login
    @RequestMapping(value={"/{msg}"}, method=RequestMethod.GET)
    public String showMessage(@PathVariable String msg, HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("1111111111");
        request.setAttribute("message", msg);
        return "index";
    }

    @Login
    @RequestMapping(value={"index"}, method=RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("index");
        return "index";
    }
}
