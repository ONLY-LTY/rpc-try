package com.lty.rpc.server;

import com.lty.rpc.api.HelloServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LTY on 6/14/16.
 */

/**
 * 服务端配置文件 主要配置了接口对应那个实现类 (相当于Spring中的注解Service)
 */
public class ConfMonitor {

    public static Map<String,Class> conf = new HashMap<String, Class>();

    static {
        conf.put("com.lty.rpc.api.IHelloService", HelloServiceImpl.class);
    }
}
