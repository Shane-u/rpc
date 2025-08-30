package com.shane.rpc.server;

/**
 * Http服务器接口
 * @Author: Shane
 * @Date: 2025/08/30/13:42
 * @Description:
 */
public interface HttpServer {

    /**
     * 启动服务器
     * @param port
     */
    void doStart(int port);
}
