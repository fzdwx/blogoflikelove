package com.lk.blog.utils;

import com.lk.blog.entity.back.Result;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author likeLove
 * @time 2020-09-04  16:14
 */
@Component
public class ResultUtils {

    /**
     * 根据sql语句结果判断返回数据结果
     *
     * @param status       sql语句运行的状态
     * @param trueMessage  正确后要发送的信息
     * @param falseMessage 错误发送的信息
     *
     * @return Result
     */
    public static Result returnBooleanDataIsNull(boolean status, String trueMessage, String falseMessage) {
        if (status) {
            return Result.ok().setMessage(trueMessage);
        }
        return Result.error().setMessage(falseMessage);
    }

    /**
     * 根据sql语句结果判断返回数据结果
     *
     * @param status       sql语句运行的状态
     * @param trueMessage  正确后要发送的信息
     * @param falseMessage 错误发送的信息
     * @param data         需要保存的数据
     *
     * @return Result
     */
    public static Result returnBooleanDataIsNull(boolean status, String trueMessage, String falseMessage, Map<String, Object> data) {
        if (status) {
            return Result.ok().setMessage(trueMessage).setData(data);
        }
        return Result.error().setMessage(falseMessage);
    }

    /**
     * 根据数据是否为空判断返回数据结果
     *
     * @param trueMessage  正确后要发送的信息
     * @param falseMessage 错误发送的信息
     * @param data         需要保存的数据
     *
     * @return Result
     */
    public static Result returnObjectDataIsNull(String trueMessage, String falseMessage, Map<String, Object> data) {
        if (data != null) {
            return Result.ok().setMessage(trueMessage).setData(data);
        }
        return Result.error().setMessage(falseMessage);
    }

    /**
     * 把一个对象放入map中
     *
     * @param key   需要放入map中的key
     * @param value 需要放入map中的value
     *
     * @return HashMap<String, Object>
     */
    public static HashMap<String, Object> objPutInMap(String key, Object value) {
        HashMap<String, Object> data = new HashMap<>(5);
        data.put(key, value);
        return data;
    }

    /**
     * 继续往这个map里面添加东西
     *
     * @param key   需要放入map中的key
     * @param value 需要放入map中的value
     * @param data  需要添加元素的map
     *
     * @return HashMap<String, Object>
     */
    public static HashMap<String, Object> objPutInMap(String key, Object value, HashMap<String, Object> data) {
        data.put(key, value);
        return data;
    }

}
