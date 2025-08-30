package com.shane.example.common.model;

import java.io.Serializable;

/**
 * @Author: Shane
 * @Date: 2025/08/30/13:19
 * @Description:
 */
public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
