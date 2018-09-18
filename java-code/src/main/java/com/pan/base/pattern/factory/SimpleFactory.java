package com.pan.base.pattern.factory;

public class SimpleFactory {

    public static Api getApi(String type) {
        if ("A".equals(type)) {
            return new ApiImpA();
        } else if ("B".equals(type)) {
            return new ApiImplB();
        }
        return null;
    }

}
