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

    public MenuElement changeElementState(String id, String states){
        if(id != null){
            menuElements.stream().filter(element -> id.equals(element.getId())).forEach(element -> element.setState(states));
        }
        return this;
    }

    public MenuElement addElementNotation(String id, String notationHtml){
        if(id != null){
            menuElements.stream().filter(element -> id.equals(element.getId())).forEach(element -> element.setNotation(notationHtml));
        }
        return this;
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
