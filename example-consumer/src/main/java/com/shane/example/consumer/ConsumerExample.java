package com.shane.example.consumer;

import com.shane.rpc.config.RpcConfig;
import com.shane.rpc.utils.ConfigUtils;

/**
 * 简易服务消费者示例
 * @Author: Shane
 * @Date: 2025/08/30/18:27
 * @Description:
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);

    }
}
