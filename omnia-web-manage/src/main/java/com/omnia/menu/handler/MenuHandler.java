package com.omnia.menu.handler;


import com.google.common.collect.ImmutableList;
import com.omnia.common.handler.ViewHandler;
import com.omnia.menu.vo.MenuElementEntry;
import com.omnia.menu.vo.MenuState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaerothe on 2015/4/19.
 */
public class MenuHandler implements ViewHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        //TODO generate module mark
        String module = request.getParameter("real");
        List<MenuElementEntry> menuList = new ArrayList<>();
        List<MenuElementEntry> childList = new ArrayList<>();
        childList.add(new MenuElementEntry("menuChild1", "EXAMPLE5", "linecons-params", "eee", null, MenuState.NORMAL.toString(), null));
        menuList.add(new MenuElementEntry("article", "EXAMPLE1", "linecons-cog", "abc", null, MenuState.NORMAL.toString(), null));
        menuList.add(new MenuElementEntry("user", "EXAMPLE2", "linecons-desktop", "bbb", null, MenuState.NORMAL.toString(), null));
        menuList.add(new MenuElementEntry("management", "EXAMPLE3", "linecons-note", "ccc", null, MenuState.NORMAL.toString(), null));
        menuList.add(new MenuElementEntry("menu", "EXAMPLE4", "linecons-star", "ddd", null, MenuState.NORMAL.toString() + " " + MenuState.OPEN.toString(),childList));

        menuList.stream().filter(menuElementEntry -> menuElementEntry.getId().equals(module)).forEach(menu ->
                menu.setState("active"));
        request.setAttribute("menuList", menuList);
    }
}
