package com.omnia.user.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.omnia.common.util.pager.DataTablePager;
import com.omnia.user.annotation.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by khaerothe on 2015/5/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final JSONObject json = new JSONObject();

    static class Temp{
        private String id;
        private String name;
        private String lastLoginTime;
        private String operate;
        public Temp(String id, String name, String lastLoginTime, String operate){
            this.id = id;
            this.name = name;
            this.lastLoginTime = lastLoginTime;
            this.operate = operate;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public String getOperate() {
            return operate;
        }
    }

    static {
        JSONArray array = new JSONArray();
        array.add(new Temp("1", "zhangsan", "2015-05-06", "<a href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("2", "lisi", "2015-05-05", "<a href=\"#\" class=\"btn btn-danger btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("3", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("4", "zhangsan", "2015-05-06", "<a href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("5", "lisi", "2015-05-05", "<a href=\"#\" class=\"btn btn-danger btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("6", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("7", "zhangsan", "2015-05-06", "<a href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("8", "lisi", "2015-05-05", "<a href=\"#\" class=\"btn btn-danger btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("9", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
        array.add(new Temp("10", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
        json.put("recordsTotal", 12);
        json.put("recordsFiltered", 12);
        json.put("data", array);
    }

    @Login
    @RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
    public String listPage(){

        return "module/user/list";
    }


    @Login
    @RequestMapping(value = {"list", ""}, method = RequestMethod.POST)
    @ResponseBody
    public String list(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        System.out.println(request.getParameter("draw"));
        System.out.println(request.getParameter("start"));
        System.out.println(request.getParameter("length"));
        if(start.equals("0")){
            return json.toString();
        }else if(start.equals("10")){
            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();
            array.add(new Temp("11", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
            array.add(new Temp("12", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
            object.put("recordsTotal", 12);
            object.put("recordsFiltered", 12);
            object.put("data", array);
            return object.toString();
        }else{
            return null;
        }

    }
}
