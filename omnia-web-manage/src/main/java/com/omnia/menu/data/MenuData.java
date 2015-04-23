package com.omnia.menu.data;

import com.google.common.collect.ImmutableList;
import com.omnia.menu.vo.MenuElement;
import com.omnia.menu.vo.MenuElementEntry;
import com.omnia.menu.vo.MenuState;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu data generate util
 * Created by khaerothe on 2015/4/23.
 */
public class MenuData {
    private MenuElement instance = null;

    public MenuData(String selectId){
        MenuElementEntry example1 = new MenuElementEntry("article", "EXAMPLE1", "linecons-cog", "abc", null, ImmutableList.of(MenuState.NORMAL), null);
        MenuElementEntry example2 = new MenuElementEntry("user", "EXAMPLE2", "linecons-desktop", "bbb", null, ImmutableList.of(MenuState.NORMAL), null);
        MenuElementEntry example3 = new MenuElementEntry("management", "EXAMPLE3", "linecons-note", "ccc", null, ImmutableList.of(MenuState.NORMAL), null);

        MenuElementEntry example5 = new MenuElementEntry("menuChild1", "EXAMPLE5", "linecons-params", "eee", null, ImmutableList.of(MenuState.NORMAL), null);

        MenuElementEntry example4 = new MenuElementEntry("menu", "EXAMPLE4", "linecons-star", "ddd", null, ImmutableList.of(MenuState.NORMAL), ImmutableList.of(example5));

        List<MenuElementEntry> elements = new ArrayList<>();
        elements.add(example1);
        elements.add(example2);
        elements.add(example3);
        elements.add(example4);

        elements.stream().filter(element -> selectId.equals(element.getId())).forEach(element -> element.setState(ImmutableList.of(MenuState.ACTIVE)));

        instance = new MenuElement(elements);
    }
    public String genHtml(){
        return instance.toString();
//        instance.changeElementState(id, ImmutableList.of(MenuState.ACTIVE));
    }

}
