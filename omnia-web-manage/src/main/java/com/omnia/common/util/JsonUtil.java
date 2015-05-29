package com.omnia.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * JSON util with jackson.
 * Created by khaerothe on 2015/4/28.
 */
public class JsonUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
    private JsonUtil(){

    }
    public static String parseJsonString(Object object) throws JsonProcessingException {
        String result = mapper.writeValueAsString(object);
        return result;
    }

    public static String findJsonString(JsonNode node, String valuePath){
        String[] paths = valuePath.split("/");
        for(String path : paths){
            node = node.path(path);
        }
        return node.textValue();
    }

    public static String findJsonString(String json, String valuePath) throws IOException {
        String[] paths = valuePath.split("/");
        JsonNode node = mapper.readTree(json);
        for(String path : paths){
            node = node.path(path);
        }
        return node.textValue();
    }

    public static <T> T parseObjectFromJson(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public static <T> T parseObjectFromJson(String json, String valuePath, Class<T> clazz) throws IOException {
        String[] paths = valuePath.split("/");
        JsonNode node = mapper.readTree(json);
        for(String path : paths){
            node = node.path(path);
        }
        if(node != null){
            return parseObjectFromJson(node.textValue(), clazz);
        }else {
            return null;
        }
    }

    public static Object parseObjectFromJson(String json, String valuePath, String clazzPath) throws IOException, ClassNotFoundException {
        String[] paths = valuePath.split("/");
        String[] clazzPaths = clazzPath.split("/");
        JsonNode node = mapper.readTree(json);
        for(String path : paths){
            node = node.path(path);
        }
        JsonNode clazzNode = mapper.readTree(json);

        for(String path : clazzPaths){
            clazzNode = clazzNode.path(path);
        }
        if(node != null && clazzNode != null){
            return parseObjectFromJson(node.textValue(), Class.forName(clazzNode.textValue()));
        }else {
            return null;
        }
    }


    public static <T> T parseObjectFromJson(JsonNode node, String valuePath, Class<T> clazz) throws IOException {
        String[] paths = valuePath.split("/");
        for(String path : paths){
            node = node.path(path);
        }
        if(node != null){
            if(clazz.isAssignableFrom(String.class)){
                return (T) node.textValue();
            }else{
                return parseObjectFromJson(node.textValue(), clazz);
            }

        }else {
            return null;
        }
    }

    public static Object parseObjectFromJson(final JsonNode json, final String valuePath, final String clazzPath) throws ClassNotFoundException, IOException {
        String[] paths = valuePath.split("/");
        String[] clazzPaths = clazzPath.split("/");
        JsonNode node = json;
        for(String path : paths){
            node = node.path(path);
        }
        JsonNode clazzNode = json;
        for(String path : clazzPaths){
            clazzNode = clazzNode.path(path);
        }
        if(node != null && clazzNode != null){
            return parseObjectFromJson(node.textValue(), Class.forName(clazzNode.textValue()));
        }else {
            return null;
        }
    }


    public static JsonNode parseJsonToJsonNode(String json) throws IOException {
        return mapper.readTree(json);
    }
    public static Map<String, String> parseJsonToMap(String json) throws IOException {
        Map<String, String> map = new HashMap<>(32);
        JsonNode node = mapper.readTree(json);
        Iterator<String> iterator = node.fieldNames();
        while(iterator.hasNext()){
            String nextName = iterator.next();
            JsonNode value = node.get(nextName);
            map.put(nextName, value.textValue());
        }
        return map;
    }

}
