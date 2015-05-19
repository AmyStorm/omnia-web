package com.omnia.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * JSON util with alibaba fastjson.
 * Created by khaerothe on 2015/4/28.
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
    private JsonUtil(){

    }
    public static String parseJsonString(Object object){
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String result = "";
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("JsonUtil Exception>>>>>>>:{}", e.toString());
        }
        return result;
    }

    public static <T> T parseObjectFromJson(String json, Class<T> clazz){
        T obj = null;
        try {
            obj = mapper.readValue(json, clazz);
        } catch (IOException e) {
            LOG.error("JsonUtil Exception>>>>>>>:{}", e.toString());
        }
        return obj;
    }
}
