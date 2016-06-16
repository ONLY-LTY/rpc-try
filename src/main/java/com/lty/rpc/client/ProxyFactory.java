package com.lty.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by LTY on 6/14/16.
 */
public class ProxyFactory  {

    public static <T> T create(Class<T> c,String ip,int port){
        InvocationHandler handler = new RpcProxy(ip,port,c);
        return (T) Proxy.newProxyInstance(c.getClassLoader(),new Class[]{c},handler);
    }
}
