package com.chen.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class BeanCopyUtil {
    public static <T, S> List<T> convertList(Collection<S> srcList, Class<T> targetClazz) {
        if (CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        }
        T target = null;
        try {
            List<T> dist = new ArrayList<T>();
            for (S src : srcList) {
                //目标类注意必须实现空构造函数
                target = targetClazz.newInstance();
                BeanUtils.copyProperties(src, target);
                dist.add(target);
            }
            return dist;
        } catch (Exception e) {
            throw new IllegalArgumentException("对象" + targetClazz.getSimpleName() + "复制属性出错", e);
        }
    }
    /**
     *
     * @param src
     * @param targetClazz
     * @return
     */
    public static <T, S> T convert(S src, Class<T> targetClazz) {
        if (src == null ) {
            return null;
        }
        try {
            //目标类注意必须实现空构造函数
            T target = targetClazz.newInstance();
            BeanUtils.copyProperties( src,target);
            return target;
        } catch (Exception e) {
            throw new IllegalArgumentException("对象" + targetClazz.getSimpleName() + "复制属性出错", e);
        }
    }
}
