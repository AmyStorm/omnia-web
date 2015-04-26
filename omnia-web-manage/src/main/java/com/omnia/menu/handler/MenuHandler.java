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

    /**
     * change the element state
     * @param father
     * @param elements
     * @param exp
     * @return
     */
    @Deprecated
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
