package com.shane.example.provider;

import com.shane.example.common.model.User;
import com.shane.example.common.service.UserService;

/**
 * 用户服务实现类
 * @Author: Shane
 * @Date: 2025/08/30/13:30
 * @Description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
