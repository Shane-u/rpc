package com.shane.example.provider;

import com.shane.example.common.service.UserService;
import com.shane.rpc.registry.LocalRegistry;
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
        // 注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        // 启动web服务
        HttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(8080);
    }
}
