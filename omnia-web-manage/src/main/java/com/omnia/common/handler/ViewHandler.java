package com.omnia.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by khaerothe on 2015/4/19.
 */
public interface ViewHandler {
    void handle(HttpServletRequest request, HttpServletResponse response);
}
