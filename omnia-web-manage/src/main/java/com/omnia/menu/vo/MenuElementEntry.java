package com.omnia.menu.vo;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by khaerothe on 2015/4/21.
 */
public class MenuElementEntry {

    /**
     * identifier of this menu element.
     */
    private String id;

    private String icon;

    private String displayName;

    private String href;

    private String notation;

    private String state;

    private List<MenuElementEntry> children;

    public MenuElementEntry(String id, String displayName, String icon, String href, String notation, String state, List<MenuElementEntry> children){
        this.id = id;
        this.icon = icon;
        this.displayName = displayName;
        this.href = href;
        this.notation = notation;
        this.state = state;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<MenuElementEntry> getChildren() {
        return children;
    }

    public void setChildren(List<MenuElementEntry> children) {
        this.children = children;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("<li class=\"");
//        if(state != null && state.contains(MenuState.OPEN)){
//            sb.append(" open ");
//        }
//        if(state != null && state.contains(MenuState.ACTIVE)){
//            sb.append(" active ");
//        }
//        sb.append("\">\n");
//        sb.append("<a href=\"").append(href).append("\">\n");
//        if(StringUtils.isNotBlank(icon)){
//            sb.append("   <i class=\"").append(icon).append("\"></i>\n");
//        }
//        sb.append("   <span class=\"title\">").append(displayName).append("</span>\n");
//        if(StringUtils.isNotBlank(notation)){
//            sb.append(notation).append("\n");
//        }
//        sb.append("</a>\n");
//        if(null != children && !children.isEmpty()){
//            sb.append("<ul>\n");
//            for(MenuElementEntry child : children){
//                sb.append("<li class=\"");
//                if(child.getState() != null && child.getState().contains(MenuState.OPEN)){
//                    sb.append(" open ");
//                }
//                if(child.getState() != null && child.getState().contains(MenuState.ACTIVE)){
//                    sb.append(" active ");
//                }
//                sb.append("\">\n");
//                sb.append(child.toString());
//                sb.append("</li>\n");
//            }
//            sb.append("</ul>\n");
//        }
//        sb.append("</li>\n");
//        return sb.toString();
//    }
}