package com.lty.rpc.demo;

import com.lty.rpc.client.ProxyFactory;
import com.lty.rpc.api.IHelloService;

/**
 * Created by LTY on 6/14/16.
 */
public class Demo {
    public static void main(String[] args) {
        IHelloService helloService = ProxyFactory.create(IHelloService.class,"localhost",8080);
        System.out.println(helloService.sayHello("Hello"));
    }
}
