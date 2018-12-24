package com.pan.exception;

/**
 * @author: panxiwen
 * @Description: TODO
 * @date 2018/12/24
 */
public class MyException extends RuntimeException {

    public MyException(String msg) {
        super(msg);
    }

    public MyException(String msg, Throwable e) {
        super(msg, e);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
