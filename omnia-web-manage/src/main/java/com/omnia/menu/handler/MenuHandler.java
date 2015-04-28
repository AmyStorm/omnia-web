package com.omnia.menu.handler;


import com.omnia.common.handler.ViewHandler;
import com.omnia.menu.data.MenuData;
import com.omnia.menu.vo.MenuElement;
import com.omnia.menu.vo.MenuElementEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by khaerothe on 2015/4/19.
 */
public class MenuHandler implements ViewHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        MenuElement element = MenuData.genMenuElements();
        request.setAttribute("menuElement", element);
    }
}
