package com.omnia.common.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omnia.common.handler.SitemeshTemplateHandler;
import com.omnia.common.handler.ViewHandler;
import com.omnia.menu.handler.MenuHandler;
import com.omnia.menu.handler.NavigationHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Application Lifecycle Listener implementation class FreemarkerFilter
 *
 */
public class FreemarkerFilter implements Filter {

	/**
	 * dynamic adding new handler to handle more view elements.
	 */
	private ViewHandler handler = new SitemeshTemplateHandler(new MenuHandler(), new NavigationHandler());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Assert.notNull(filterConfig, "FilterConfig must not be null");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		handler.handle((HttpServletRequest)request,
				(HttpServletResponse)response);
	}

	@Override
	public void destroy() {
		handler = null;
	}
	
}
