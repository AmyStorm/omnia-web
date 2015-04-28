package com.omnia.menu.vo;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khaerothe on 2015/4/20.
 */
public class MenuElement {

    private List<MenuElementEntry> menuElements = new ArrayList<>();

    public MenuElement(List<MenuElementEntry> menuElements){
        this.menuElements = menuElements;
    }

    public List<MenuElementEntry> getMenuElements() {
        return menuElements;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul id=\"main-menu\" class=\"main-menu\">\n");
        for(MenuElementEntry menuElement : menuElements){
            sb.append(menuElement.toString());
        }
        sb.append("</ul>\n");
        return sb.toString();
    }

}
