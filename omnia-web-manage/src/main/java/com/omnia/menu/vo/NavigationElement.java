package com.omnia.menu.vo;

/**
 * Created by khaerothe on 2015/5/7.
 */
public class NavigationElement {
    private String name;
    private String href;

    public NavigationElement(String name, String href){
        this.name = name;
        this.href = href;
    }


    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

}
