package com.omnia.menu.vo;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by khaerothe on 2015/4/20.
 */
public class MenuElement {

    private String icon;

    private String displayName;

    private String href;

    private String addon;

    private List<MenuState> state;

    private List<MenuElement> children;

    public MenuElement(){

    }

    public String getIcon() {
        return icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getHref() {
        return href;
    }

    public String getAddon() {
        return addon;
    }

    public List<MenuState> getState() {
        return state;
    }

    public List<MenuElement> getChildren() {
        return children;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }

    public void setState(List<MenuState> state) {
        this.state = state;
    }

    public void setChildren(List<MenuElement> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<li class=\"");
        if(state != null && state.contains(MenuState.OPEN)){
            sb.append(" open ");
        }
        if(state != null && state.contains(MenuState.ACTIVE)){
            sb.append(" active ");
        }
        sb.append("\">\n");
        sb.append("<a href=\"").append(href).append("\">\n");
        if(StringUtils.isNotBlank(icon)){
            sb.append("   <i class=\"").append(icon).append("\"></i>\n");
        }
        sb.append("   <span class=\"title\">").append(displayName).append("</span>\n");
        sb.append("</a>\n");
        if(null != children && !children.isEmpty()){
            sb.append("<ul>\n");
                for(MenuElement child : children){
                    sb.append("<li class=\"");
                    if(child.getState() != null && child.getState().contains(MenuState.OPEN)){
                        sb.append(" open ");
                    }
                    if(child.getState() != null && child.getState().contains(MenuState.ACTIVE)){
                        sb.append(" active ");
                    }
                    sb.append("\">\n");
                    sb.append(child.toString());
                    sb.append("</li>\n");
                }
            sb.append("</ul>\n");
        }
        sb.append("</li>\n");
        return sb.toString();
    }
}

enum MenuState{
    OPEN,
    ACTIVE,
    NORMAL
}