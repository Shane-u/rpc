package com.shane.rpc.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.yaml.YamlUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 配置工具类，读取配置文件并且返回对象，简化调用
 * @Author: Shane
 * @Date: 2025/08/30/17:33
 * @Description:
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass,prefix,"");
    }

    /**
     * 加载配置对象，支持区分环境
     *
     * @param tClass
     * @param prefix
     * @param environment
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        List<String> formats = Arrays.asList(".properties",".yaml",".yml"); // 优先顺序如下
        for(String format : formats) {
            configFileBuilder = new StringBuilder("application");
            String path = configFileBuilder.append(format).toString();
            if(!resourceExists(path)) continue;
            switch (format) {
                case ".properties":
                    Props props = new Props(configFileBuilder.toString());
                    return props.toBean(tClass, prefix);
                case ".yaml":
                    Dict dict = YamlUtil.loadByPath(path);
                    return BeanUtil.copyProperties(dict.getBean(prefix), tClass);
                default:
                    throw new RuntimeException("文件不合法");
            }
        }
        return null;
    }

    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    private static boolean resourceExists(String path) {
        // 使用当前线程的上下文类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(path) != null;
    }
}
