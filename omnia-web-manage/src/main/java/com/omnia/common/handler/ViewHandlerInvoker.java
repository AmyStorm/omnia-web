package com.omnia.common.handler;

import com.omnia.menu.handler.MenuHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaerothe on 2015/4/19.
 */
public class ViewHandlerInvoker {

    private List<ViewHandler> viewHandlers = new ArrayList<>();

    private static ViewHandlerInvoker instance = new ViewHandlerInvoker();

    private ViewHandlerInvoker(){
        viewHandlers.add(new MenuHandler());
    }

    public static ViewHandlerInvoker getInstance(){
        return instance;
    }

    public void invoke(HttpServletRequest request, HttpServletResponse response){
        for(ViewHandler handler : viewHandlers){
            handler.handle(request, response);
        }
    }
}
