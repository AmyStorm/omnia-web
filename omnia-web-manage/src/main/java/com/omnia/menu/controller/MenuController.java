package com.omnia.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/4/17.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {


    @RequestMapping(value={"list", ""}, method= RequestMethod.GET)
    public String showMessage(HttpServletRequest request, HttpServletResponse response)
    {
        return "left-menu";
    }
}
