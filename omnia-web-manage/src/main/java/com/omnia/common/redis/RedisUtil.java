package com.omnia.common.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class RedisUtil {

    private static final Log LOG = LogFactory.getLog(RedisUtil.class);
    //Redis服务器IP
    private static String ADDR = "127.0.0.1";

    //Redis的端口号
    private static int PORT = 6379;

    //访问密码
    private static String AUTH = "admin";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_TOTAL = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static{
        InputStream in = null;
        Properties properties = new Properties();
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/redis.properties");
            properties.load(in);

            Object address = properties.get("address");
            Object port = properties.get("port");
            Object auth = properties.get("auth");
            Object maxTotal = properties.get("maxTotal");
            Object maxIdle = properties.get("maxIdle");
            Object maxWait = properties.get("maxWait");
            Object timeout = properties.get("timeout");
            Object testOnBorrow = properties.get("testOnBorrow");
            if(address != null){
                ADDR = String.valueOf(address);
            }
            if(port != null){
                PORT = Integer.valueOf(port.toString());
            }
            if(auth != null){
                AUTH = String.valueOf(auth);
            }
            if(maxTotal != null){
                MAX_TOTAL = Integer.valueOf(maxTotal.toString());
            }
            if(maxIdle != null){
                MAX_IDLE =  Integer.valueOf(maxIdle.toString());
            }
            if(maxWait != null){
                MAX_WAIT =  Integer.valueOf(maxWait.toString());
            }
            if(timeout != null){
                TIMEOUT =  Integer.valueOf(timeout.toString());
            }
            if(testOnBorrow != null){
                TEST_ON_BORROW = Boolean.valueOf(testOnBorrow.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxActive(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxWaitMillis(MAX_WAIT);
//            config.setMaxWait(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
//            jedisPool.returnResource(jedis);
        }
    }
}