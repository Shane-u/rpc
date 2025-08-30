package com.shane.example.common.service;

import com.shane.example.common.model.User;

/**
 * 用户服务
 * @Author: Shane
 * @Date: 2025/08/30/13:26
 * @Description:
 */
public interface UserService {

    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);
}
