package com.shane.example.consumer;

import com.shane.example.common.model.User;
import com.shane.example.common.service.UserService;
import com.shane.rpc.proxy.ServiceProxyFactory;

/**
 * 简易服务消费者示例
 * @Author: Shane
 * @Date: 2025/08/30/13:34
 * @Description:
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        // 需要获得 UserService 的实现类对象
        // 静态代理
        // UserService userService = new UserServiceProxy();
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("shane");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
