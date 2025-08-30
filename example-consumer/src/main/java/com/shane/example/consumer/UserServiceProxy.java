package com.shane.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.shane.example.common.model.User;
import com.shane.example.common.service.UserService;
import com.shane.rpc.model.RpcRequest;
import com.shane.rpc.model.RpcResponse;
import com.shane.rpc.serializer.JdkSerializer;

/**
 * 静态代理
 *
 * @Author: Shane
 * @Date: 2025/08/30/15:00
 * @Description:
 */
public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        // 指定序列化器
        JdkSerializer serializer = new JdkSerializer();

        // 发送请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class}) // 创建单元素数组
                .args(new Object[]{user})
                .build();

        try{
            // 序列化请求
            byte[] serialized = serializer.serialize(rpcRequest);
            // 发送http请求
            HttpResponse httpResponse = HttpRequest.post("http://localhost:8080").body(serialized).execute();
            // 接收二进制响应
            byte[] result = httpResponse.bodyBytes();
            // 反序列化为封装的rpcResponse
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            // 获取响应的对象
            return (User)rpcResponse.getData();
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
}
