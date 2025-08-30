package com.shane.rpc.server;

import io.vertx.core.Vertx;

/**
 * @Author: Shane
 * @Date: 2025/08/30/13:44
 * @Description:
 */
public class VertxHttpServer implements HttpServer {


    /**
     * 启动服务器
     * @param port
     */
    @Override
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 http 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
//        server.requestHandler(request -> {
//            // 处理 http 请求
//            System.out.println("Received request:" + request.method() + " " + request.uri());
//
//            // 发送 http 响应
//            request.response()
//                    .putHeader("content-type", "text/plain")
//                    .end("Hello from Vert.x HTTP server!");
//        });
        server.requestHandler(new HttpServerHandler());

        // 启动http服务器并监听指定端口
        server.listen(port,httpServerAsyncResult -> {
            if (httpServerAsyncResult.succeeded()) {
                System.out.println("Server is now listening on port:"+port);
            } else {
                System.err.println("Failed to start server:" + httpServerAsyncResult.cause());
            }
        });
    }
}
