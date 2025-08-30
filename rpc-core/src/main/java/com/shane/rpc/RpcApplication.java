package com.shane.rpc;

import com.shane.rpc.config.RpcConfig;
import com.shane.rpc.constant.RpcConstant;
import com.shane.rpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * RPC 框架应用
 * 相当于holder，存放了项目全局用到的变量。使用的是双检锁单例模式实现的
 *
 * @Author: Shane
 * @Date: 2025/08/30/17:54
 * @Description:
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param initRpcConfig
     */
    public static void init(RpcConfig initRpcConfig) {
        rpcConfig = initRpcConfig;
        log.info("rpc init, config = {}", initRpcConfig.toString());
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig); // 调用有参数的方法，复用逻辑
    }


    /**
     * 获取配置
     * @return
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            // 需要加锁 配置文件的写入需要互斥
            // 排队
            synchronized (RpcApplication.class) {
                // 防止队列后面的配置重复初始化，确保单例的唯一初始化
                if (rpcConfig == null) {
                    // 懒加载
                    init();
                }
            }
        }
        return rpcConfig;
    }

}
