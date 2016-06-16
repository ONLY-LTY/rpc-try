package com.lty.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by LTY on 6/14/16.
 */
public class RpcThread extends Thread{
    private Socket socket;
    public RpcThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;
        try {
            is = new ObjectInputStream(socket.getInputStream());
            RpcObject rpcObject = (RpcObject) is.readObject();
            Object o = getObject(rpcObject.getC());
            Object res = executeMethod(o,rpcObject.getMethodName(),rpcObject.getArgs());

            os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(res);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null)
                    is.close();
                if(os!=null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object executeMethod(Object o, String methodName, Object[] args) {
        Object res = null;
        Class[] argClass = new Class[args.length];
        for(int i = 0 ; i < args.length ; i++){
            Object arg = args[i];
            argClass[i] = arg.getClass();
        }
        try {
            Method m = o.getClass().getMethod(methodName,argClass);
            res = m.invoke(o,args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  res;
    }

    private Object getObject(Class c){
        Object o = null;
        try {
            o = ConfMonitor.conf.get(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}
