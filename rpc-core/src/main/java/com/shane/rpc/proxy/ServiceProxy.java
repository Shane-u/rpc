package com.shane.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.shane.rpc.model.RpcRequest;
import com.shane.rpc.model.RpcResponse;
import com.shane.rpc.serializer.JdkSerializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Jdk动态代理
 * @Author: Shane
 * @Date: 2025/08/30/15:24
 * @Description:
 */
public class ServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 和静态代理几乎一致
        // 指定序列化器
        JdkSerializer serializer = new JdkSerializer();

        // 发送请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes()) // 创建单元素数组
                .args(args)
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
            return rpcResponse.getData();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
