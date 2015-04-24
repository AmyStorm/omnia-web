package com.omnia.menu.handler;


import com.omnia.common.handler.ViewHandler;
import com.omnia.menu.vo.MenuElementEntry;

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
        String module = request.getParameter("menu");
        System.out.println(module);
        List<MenuElementEntry> menuList = new ArrayList<>();
        List<MenuElementEntry> childList = new ArrayList<>();
        childList.add(new MenuElementEntry("menuChild1", "EXAMPLE5", "linecons-params", "eee", null, "", null));
        menuList.add(new MenuElementEntry("article", "EXAMPLE1", "linecons-cog", "abc", null, "", null));
        menuList.add(new MenuElementEntry("user", "EXAMPLE2", "linecons-desktop", "bbb", null, "", null));
        menuList.add(new MenuElementEntry("management", "EXAMPLE3", "linecons-note", "ccc", null, "", null));
        menuList.add(new MenuElementEntry("menu", "EXAMPLE4", "linecons-star", "ddd", null, "", childList));

        MenuElementEntry entry = getMenuELement(null, menuList, module);
        assert entry != null;


        System.out.println(entry);
        request.setAttribute("menuList", menuList);
    }

    public MenuElementEntry getMenuELement(MenuElementEntry father, List<MenuElementEntry> elements, String exp){

        for(MenuElementEntry element : elements){
            if(element.getChildren() != null && element.getChildren().size() > 0){
                return getMenuELement(element, element.getChildren(), exp);
            }else{
                if(element.getId().equals(exp)){
                    if(father != null){
                        father.setState("opened active");
                    }
                    element.setState("active");
                    return element;
                }else{
                    continue;
                }
            }
        }
        return null;
    }
}
