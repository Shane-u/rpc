package com.shane.rpc.serializer;

import java.io.IOException;

/**
 * 序列化器
 * @Author: Shane
 * @Date: 2025/08/30/14:15
 * @Description:
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;
    /**
     * 反序列化
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
