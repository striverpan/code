package com.pan.system;

/**
 * Created by pan on 2017/9/11.
 */
public class SystemException extends RuntimeException {


    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(String msg, Throwable e) {
        super(msg, e);
    }
}
