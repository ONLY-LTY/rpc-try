package com.lty.rpc.api;

/**
 * Created by LTY on 6/14/16.
 */
public class HelloServiceImpl implements IHelloService{
    public String sayHello(String content) {
        return "Welcome RPC " + content;
    }
}
