package com.shane.example.provider;

import com.shane.rpc.server.HttpServer;
import com.shane.rpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 * @Author: Shane
 * @Date: 2025/08/30/13:32
 * @Description:
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        // 启动web服务
        HttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(8080);
    }
}
