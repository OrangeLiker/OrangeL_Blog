package org.orange.utils;

import org.orange.domain.entity.Article;
import org.orange.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName BeanCopyUtils
 * @Description TODO
 * @Author WangZJ0908
 * @Date 2024/8/4
 * @Version: 1.0
 */
public class BeanCopyUtils {
    private BeanCopyUtils(){

    }
    //单个对象的拷贝,泛型方法
    public static <V> V copyBean(Object source,Class<V> clazz){
        //创建目标对象
        V result = null;
        try {
            //实现属性拷贝
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        //返回结果
        return result;
    }
    //集合对象的拷贝
    public static <O,V> List<V> copyBeanList(List<O> resource, Class<V> clazz){//O表示原对象，V表示目标对象
        //以Stream流形式
        return resource.stream()
                .map(obj->copyBean(obj,clazz))
                .collect(Collectors.toList());
    }
}
