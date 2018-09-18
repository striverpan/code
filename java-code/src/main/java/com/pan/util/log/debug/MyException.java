package com.pan.util.log.debug;

/**
 * Created by pan on 2018/1/23.
 */
public class MyException extends RuntimeException {

    private String errorCode;
    private String msg;

    public MyException() {

    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
    }

}
