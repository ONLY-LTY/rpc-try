package com.lty.rpc.client;

import com.lty.rpc.server.RpcObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by LTY on 6/14/16.
 */
public class RpcProxy implements InvocationHandler {
    private String ip ;
    private int port;
    private Class aClass;
    public <T> RpcProxy(String ip, int port, Class<T> c) {
        this.ip = ip;
        this.port = port;
        this.aClass = c;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = null;
        Socket socket = new Socket(ip,port);
        RpcObject request = new RpcObject(aClass,method.getName(),args);
        ObjectInputStream is = null;
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(request);
            os.flush();
            is = new ObjectInputStream(socket.getInputStream());
            res = is.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (os!=null)
                os.close();
            if(is!=null)
                is.close();
        }
        return res;
    }
}
