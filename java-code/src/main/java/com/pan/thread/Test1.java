package com.pan.thread;

public class Test1 {

    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                pong();
            }
        };
        thread.start();
        System.out.println("ping");
    }

    static void pong() {
        System.out.println("pong");
    }
}
