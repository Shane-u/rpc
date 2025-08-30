package com.shane.example.provider;

import com.shane.example.common.service.UserService;
import com.shane.rpc.RpcApplication;
import com.shane.rpc.registry.LocalRegistry;
import com.shane.rpc.server.HttpServer;
import com.shane.rpc.server.VertxHttpServer;

/**
 * @Author: Shane
 * @Date: 2025/08/30/18:33
 * @Description:
 */
public class ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
