package com.omnia.common.spring.processor;

import com.google.common.collect.ImmutableList;
import com.omnia.menu.vo.MenuElement;
import com.omnia.menu.vo.MenuElementEntry;
import com.omnia.menu.vo.MenuState;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by khaerothe on 2015/4/23.
 */
@Component
public class MenuInitProcessor implements InitializingBean{

    private static final Log LOG = LogFactory.getLog(MenuInitProcessor.class);
    @Override
    public void afterPropertiesSet() throws Exception {

//        MenuElementEntry entry = new MenuElementEntry("EXAMPLE1", "linecons-cog", "abc", null, ImmutableList.of(MenuState.NORMAL), null);
//        MenuElementEntry entry = new MenuElementEntry("EXAMPLE2", "linecons-desktop", "bbb", null, ImmutableList.of(MenuState.NORMAL), null);
//        MenuElementEntry entry = new MenuElementEntry("EXAMPLE3", "linecons-note", "ccc", null, ImmutableList.of(MenuState.NORMAL), null);
//        MenuElementEntry entry = new MenuElementEntry("EXAMPLE4", "linecons-star", "ddd", null, ImmutableList.of(MenuState.NORMAL), null);
//        MenuElementEntry entry = new MenuElementEntry("EXAMPLE5", "linecons-params", "eee", null, ImmutableList.of(MenuState.NORMAL), null);
//
//        MenuElement element = new MenuElement(ImmutableList.of(entry));


    }
}
