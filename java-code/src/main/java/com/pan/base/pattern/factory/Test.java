package com.pan.base.pattern.factory;

public class Test {

    public static void main(String[] args) {

        Api apiA = SimpleFactory.getApi("A");
        apiA.test();
        Api apiB = SimpleFactory.getApi("B");
        apiB.test();
    }


}
