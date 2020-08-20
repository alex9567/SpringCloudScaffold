package com.chen.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.List;

/**
 * Redis工具类
 *
 * @author chenhao
 * @date 2020.5.07
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 向Redis中存值，永久有效
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            return "0";
        } finally {
            close(jedis);
        }
    }

    /**
     * 根据传入Key获取指定Value
     */
    public String get(String key) {
        Jedis jedis = null;
        String value;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            return "0";
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 校验Key值是否存在
     */
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            return false;
        } finally {
            close(jedis);
        }
    }

    /**
     * 删除指定Key-Value
     */
    public Long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            return 0L;
        } finally {
            close(jedis);
        }
    }
    /**
     * 增加用户地理位置的坐标
     * @param key
     * @param coordinate
     * @param memberName
     * @return
     */
    public Long geoadd(String key, GeoCoordinate coordinate, String memberName) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, coordinate.getLongitude(), coordinate.getLatitude(), memberName);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }
    /**
     * 删除一个地理位置
     * @param key
     * @param memberName
     * @return
     */
    public Long geoRemove(String key, String memberName) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrem(key,memberName);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 根据给定地理位置坐标获取指定范围的用户地理位置集合（匹配位置的经纬度 + 相隔距离 + 从近到远排序）
     * @param key
     * @param coordinate
     * @param radius 指定半径
     * @param geoUnit 单位
     * @return
     */
    public List<GeoRadiusResponse> geoRadius(String key, GeoCoordinate coordinate, int radius, GeoUnit geoUnit) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.georadius(key, coordinate.getLongitude(), coordinate.getLatitude(), radius, geoUnit);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }

    /**
     * 查询两位置距离
     * @param key
     * @param member1
     * @param member2
     * @param unit
     * @return
     */
    public Double geoDist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geodist(key, member1, member2, unit);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }


    public List<GeoCoordinate> geoPos(String key, String... var2) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geopos(key,var2);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            close(jedis);
        }
        return null;
    }
    // 以上为常用方法，更多方法自行百度

    /**
     * 释放连接
     */
    private static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
