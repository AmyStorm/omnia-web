package com.omnia.menu.data;

import com.google.common.collect.ImmutableList;
import com.omnia.menu.vo.MenuElement;
import com.omnia.menu.vo.MenuElementEntry;
import java.util.List;

/**
 * Menu data generate util
 * Created by khaerothe on 2015/4/23.
 */
public class MenuData {
    private static final List<MenuElementEntry> MENU_ELEMENTS = ImmutableList.of(
            new MenuElementEntry("article", "EXAMPLE1", "linecons-cog", "abc", null),
            new MenuElementEntry("user", "user management", "linecons-desktop", "user", null),
            new MenuElementEntry("management", "EXAMPLE3", "linecons-note", "ccc", null),
            new MenuElementEntry("menu", "EXAMPLE4", "linecons-star", "ddd",
                    ImmutableList.of(new MenuElementEntry("menuChild1", "EXAMPLE5", "linecons-params", "eee", null))));

    private static final MenuElement MENU_INSTANCE = new MenuElement(MENU_ELEMENTS);

    private MenuData(){

    }
    public static MenuElement genMenuElements(){
//        MENU_ELEMENTS.stream().filter(
//                menuElementEntry -> menuElementEntry.getId().equals(selectId)).forEach(
//                menuElementEntry -> menuElementEntry.setState(isOpened == null || isOpened ? MenuState.NORMAL.toString() : MenuState.ACTIVE.toString() + " " + MenuState.OPEN.toString()));
        return MENU_INSTANCE;
    }

}
