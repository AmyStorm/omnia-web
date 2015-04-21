package com.omnia.menu.handler;

import com.google.common.collect.ImmutableList;
import com.omnia.common.handler.ViewHandler;
import com.omnia.menu.vo.MenuElement;
import com.omnia.menu.vo.MenuElementEntry;
import com.omnia.menu.vo.MenuState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by khaerothe on 2015/4/19.
 */
public class MenuHandler implements ViewHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        MenuElementEntry entry = new MenuElementEntry("linecons-cog", "EXAMPLE1", "abc", null, ImmutableList.of(MenuState.NORMAL), null);
        MenuElement element = new MenuElement(ImmutableList.of(entry));

        request.setAttribute("menu", element.toString());
    }
}
