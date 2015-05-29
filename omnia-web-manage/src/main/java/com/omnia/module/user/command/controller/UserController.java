package com.omnia.module.user.command.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.omnia.common.util.JsonUtil;
import com.omnia.module.user.command.annotation.Login;
import com.omnia.module.user.command.domain.User;
import com.omnia.module.user.query.UserListener;
import com.omnia.module.user.query.repository.impl.UserQueryRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by khaerothe on 2015/5/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private static final Map json = new HashMap();

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
        List<Temp> array = new ArrayList<>();
        for(Map.Entry<String, User> entry : UserQueryRepositoryImpl.inMemoryUser.entrySet()){
            User user = entry.getValue();
            array.add(new Temp(user.getIdentifier(), user.getUserName(), user.getCreateTime().toString(), "<a href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">Edit</a>"));

        }
//        array.add(new Temp("1", "zhangsan", "2015-05-06", "<a href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("2", "lisi", "2015-05-05", "<a href=\"#\" class=\"btn btn-danger btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("3", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("4", "zhangsan", "2015-05-06", "<a href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("5", "lisi", "2015-05-05", "<a href=\"#\" class=\"btn btn-danger btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("6", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("7", "zhangsan", "2015-05-06", "<a href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("8", "lisi", "2015-05-05", "<a href=\"#\" class=\"btn btn-danger btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("9", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
//        array.add(new Temp("10", "wangwu", "2015-05-04", "<a href=\"#\" class=\"btn btn-info btn-sm btn-icon icon-left\">Edit</a>"));
        json.put("recordsTotal", UserQueryRepositoryImpl.inMemoryUser.size());
        json.put("recordsFiltered", UserQueryRepositoryImpl.inMemoryUser.size());
        json.put("data", array);
    }

    @Autowired
    private UserListener userListener;

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
//        System.out.println(request.getParameter("draw"));
//        System.out.println(request.getParameter("start"));
//        System.out.println(request.getParameter("length"));
        if(start.equals("0")){
            Map json = new HashMap<>();
            List<Temp> array = new ArrayList<>();
            for(Map.Entry<String, User> entry : UserQueryRepositoryImpl.inMemoryUser.entrySet()){
                User user = entry.getValue();
                array.add(new Temp(user.getIdentifier(), user.getUserName(), user.getCreateTime().toString(), "<a href=\"#\" class=\"btn btn-secondary btn-sm btn-icon icon-left\">Edit</a>"));

            }
            json.put("recordsTotal", UserQueryRepositoryImpl.inMemoryUser.size());
            json.put("recordsFiltered", UserQueryRepositoryImpl.inMemoryUser.size());
            json.put("data", array);
            try {
                return JsonUtil.parseJsonString(json);
            } catch (JsonProcessingException e) {
                LOG.error("json convert error", e);
                return null;
            }
        }else if(start.equals("10")){
            Map object = new HashMap();
            List array = new ArrayList();
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
