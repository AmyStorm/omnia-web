package com.omnia.common.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * default sitemesh handler resolves page decorating.
 * Created by khaerothe on 2015/4/19.
 */
public class SitemeshTemplateHandler implements ViewHandler {

    private static Log LOG = LogFactory.getLog(SitemeshTemplateHandler.class);
    private List<ViewHandler> handlerList = new ArrayList<>();

    public SitemeshTemplateHandler(ViewHandler... handlers){
        for(ViewHandler handler : handlers){
            handlerList.add(handler);
        }
    }
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) {

        for(ViewHandler handler : handlerList){
            handler.handle(request, response);
        }

        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);

        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        if (null == ctx) {
            throw new ExceptionInInitializerError("spring context is not loaded!");
        }
        try {
            String name = request.getRequestURI();
            name = name.substring(1, name.lastIndexOf(".dec"));
            name = name.substring(name.lastIndexOf("/", name.lastIndexOf("/") - 1));
            FreeMarkerViewResolver viewResolver = (FreeMarkerViewResolver) ctx.getBean("templateViewResolver");
            View view = viewResolver.resolveViewName(name, locale);
            view.render(null, request, response);
        } catch (Exception e) {
            LOG.error(e);
        }
    }
}
