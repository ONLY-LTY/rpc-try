package com.lty.rpc.server;

import java.io.Serializable;

/**
 * Created by LTY on 6/14/16.
 */
public class RpcObject implements Serializable {
    private static final long serializableUID = 1L;

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    private Class c;
    private String methodName;
    private Object[] args;

    public RpcObject(Class aClass, String name, Object[] args){
        this.args = args;
        this.methodName = name;
        this.c = aClass;
    }

}
