package com.pan.thread;

import java.util.Map;

public class ThreadLocalTest {

    private static InheritableThreadLocal<Integer> threadLocalStr = new InheritableThreadLocal<>();


    public static void main(String[] args) {

        threadLocalStr.set(100);

        System.out.println(threadLocalStr.get());

        ThreadLocalThread thread1 = new ThreadLocalThread(threadLocalStr);
        ThreadLocalThread thread2 = new ThreadLocalThread(threadLocalStr);
        ThreadLocalThread thread3 = new ThreadLocalThread(threadLocalStr);
        ThreadLocalThread thread4 = new ThreadLocalThread(threadLocalStr);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


    }


}

class ThreadLocalThread extends Thread {

    public InheritableThreadLocal<Integer> getValue() {
        return value;
    }

    InheritableThreadLocal<Integer> value;

    public ThreadLocalThread(InheritableThreadLocal<Integer> value) {
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            value.set(value.get()+1);
            System.out.println(Thread.currentThread().getName()+"  " + (value.get()));
        }
    }
}