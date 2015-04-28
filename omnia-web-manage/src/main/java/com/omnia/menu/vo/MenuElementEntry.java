package com.omnia.menu.vo;

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

    private List<MenuElementEntry> children;

    public MenuElementEntry(String id, String displayName, String icon, String href, List<MenuElementEntry> children){
        this.id = id;
        this.icon = icon;
        this.displayName = displayName;
        this.href = href;
        this.children = children;
    }

    public String getId() {
        return id;
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

    public String getNotation() {
        return notation;
    }

    public List<MenuElementEntry> getChildren() {
        return children;
    }
}