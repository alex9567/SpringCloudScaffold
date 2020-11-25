package com.chen.core.sign;

import com.chen.service.requestDTO.OpenRequestDTO;
import com.chen.service.requestDTO.TestOpenRequestDTO;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckSign {
    private static String secretKey = "123456";
    private static String accessKey = "testUser";

    // 通过CacheBuilder构建一个缓存实例
    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(10000) // 设置缓存的最大容量
            .expireAfterWrite(15, TimeUnit.MINUTES) // 设置缓存在写入一分钟后失效
            .concurrencyLevel(10) // 设置并发级别为10
            .recordStats() // 开启缓存统计
            .build();

    public static void main(String[] args) {
        TestOpenRequestDTO openRequestDTO = new TestOpenRequestDTO();
        openRequestDTO.setAccessKey(accessKey);
        openRequestDTO.setNonce("qwe");
        //先执行一次获得对应的sign
        openRequestDTO.setSign("F36A9A4436D5C8B42A410C6A061FE333");
        openRequestDTO.setParamA("a");
        openRequestDTO.setParamB("b");
        //注意设置时间与当前时间
        LocalDateTime dateTime = LocalDateTime.of(2020, 11, 20, 11, 40, 0);
        openRequestDTO.setTimestamp(dateTime);
        OpenRequestDTO openRequestDTONext = new OpenRequestDTO();
        BeanUtils.copyProperties(openRequestDTO,openRequestDTONext);
        Gson gson = new Gson();
        String a = gson.toJson(openRequestDTO);
        System.out.println("请求json:"+a);
        System.out.println(CheckSign.check(openRequestDTO));
        System.out.println(CheckSign.check(openRequestDTONext));
    }

    public static boolean check(OpenRequestDTO openRequestDTO) {
        if (!checkParams(openRequestDTO)) {
            return false;
        }
        if (!checkTime(openRequestDTO)) {
            return false;
        }
        if (!checkNonce(openRequestDTO)) {
            return false;
        }
        String sign = openRequestDTO.getSign();
        openRequestDTO.setSign(null);
        Gson gson = new Gson();
        String a = gson.toJson(openRequestDTO);
        Map<String, Object> map = gson.fromJson(a, Map.class);
        String result = CheckSign.sign(map, secretKey);
        System.out.println("result:" + result);
        if (result.equals(sign)) {
            return true;
        }
        return false;
    }

    /**
     * 参数校验
     * @param openRequestDTO
     * @return
     */
    public static boolean checkParams(OpenRequestDTO openRequestDTO) {
        if (openRequestDTO.getAccessKey() == null || openRequestDTO.getNonce() == null || openRequestDTO.getSign() == null || openRequestDTO.getTimestamp() == null) {
            System.out.println("校验失败，有参数为空");
            return false;
        }
        return true;
    }

    /**
     * 时间校验
     * @param openRequestDTO
     * @return
     */
    public static boolean checkTime(OpenRequestDTO openRequestDTO) {
        Duration duration = Duration.between(openRequestDTO.getTimestamp(), LocalDateTime.now());
        if (duration.toMinutes() > 15 || duration.toMinutes() < 0) {
            System.out.println("校验失败，时间超时");
            return false;
        }
        return true;
    }

    /**
     * 随机数校验
     * @param openRequestDTO
     * @return
     */
    public static boolean checkNonce(OpenRequestDTO openRequestDTO) {
        String key = openRequestDTO.getNonce();
        String value = null;
        value = cache.getIfPresent(key);
        if (value != null) {
            System.out.println("校验失败，重复请求");
            return false;
        }
        cache.put(openRequestDTO.getNonce(), openRequestDTO.getTimestamp().toString());
        return true;
    }

    /**
     * 签名校验
     * @param attrs
     * @param signKey
     * @return
     */
    public static String sign(Map<String, Object> attrs, String signKey) {
        StringBuilder sb = new StringBuilder();
        Set<String> strings = attrs.keySet();
        ArrayList<String> keys = new ArrayList<>(strings);
        Collections.sort(keys);
        for (String key : keys) {
            Object value = attrs.get(key);
            buildField(sb, key, value);
        }
        sb.append(signKey);
        System.out.println("最终参数为sb:" + sb);
        return DigestUtils.md5Hex(sb.toString().getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

    /**
     * 参数构建
     * @param sb
     * @param key
     * @param value
     */
    private static void buildField(StringBuilder sb, String key, Object value) {
        if (null != value) {
            //第二次
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(key);
            sb.append("=");
            sb.append(value);
        }
    }
}
