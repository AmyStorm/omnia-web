package com.omnia.menu.handler;

import com.omnia.common.handler.ViewHandler;
import com.omnia.menu.data.MenuData;
import com.omnia.menu.vo.MenuElement;
import com.omnia.menu.vo.NavigationElement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaerothe on 2015/5/6.
 */
public class NavigationHandler implements ViewHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        List<NavigationElement> elements = new ArrayList<>();
        //TODO menu elements can convert to navigation path element?
        MenuElement menuElements = MenuData.genMenuElements();
        NavigationElement element = new NavigationElement("user management", "user");
        elements.add(element);
        request.setAttribute("navigation", elements);
    }
}
