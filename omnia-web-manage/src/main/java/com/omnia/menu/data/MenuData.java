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
    private static final List<MenuElementEntry> MENU_ELEMENTS = ImmutableList.of(
            new MenuElementEntry("article", "EXAMPLE1", "linecons-cog", "abc", null, MenuState.NORMAL.toString(), null),
            new MenuElementEntry("user", "EXAMPLE2", "linecons-desktop", "bbb", null, MenuState.NORMAL.toString(), null),
            new MenuElementEntry("management", "EXAMPLE3", "linecons-note", "ccc", null, MenuState.NORMAL.toString(), null),
            new MenuElementEntry("menu", "EXAMPLE4", "linecons-star", "ddd", null, MenuState.NORMAL.toString() + " " + MenuState.OPEN.toString(),
                    ImmutableList.of(new MenuElementEntry("menuChild1", "EXAMPLE5", "linecons-params", "eee", null, MenuState.NORMAL.toString(), null))));

    private static final MenuElement MENU_INSTANCE = new MenuElement(MENU_ELEMENTS);

    private MenuData(){

    }
    public static String genHtml(String selectId, Boolean isOpened){
//        MENU_ELEMENTS.stream().filter(element -> selectId.equals(element.getId())).forEach(element -> element.setState(ImmutableList.of(MenuState.ACTIVE)));
        MENU_ELEMENTS.stream().filter(
                menuElementEntry -> menuElementEntry.getId().equals(selectId)).forEach(
                menuElementEntry -> menuElementEntry.setState(isOpened == null || isOpened ? MenuState.NORMAL.toString() : MenuState.ACTIVE.toString() + " " + MenuState.OPEN.toString()));
        return MENU_INSTANCE.toString();
//        instance.changeElementState(id, ImmutableList.of(MenuState.ACTIVE));
    }

}
