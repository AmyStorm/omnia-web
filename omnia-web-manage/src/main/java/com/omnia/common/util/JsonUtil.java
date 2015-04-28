package com.omnia.common.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by khaerothe on 2015/4/28.
 */
public class JsonUtil {
    private JsonUtil(){

    }
    public static String parseJsonString(Object object){
        return JSON.toJSONString(object, true);
    }

    public static <T> T parseObjectFromJson(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }
}
