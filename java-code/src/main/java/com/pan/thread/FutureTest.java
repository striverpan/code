package com.pan.thread;

import java.util.concurrent.*;

/**
 * @author: panxiwen
 * @Description: TODO
 * @date 2018/11/28
 */
public class FutureTest {


    static class Task implements Callable {

        @Override
        public Object call() throws Exception {
            return "hello";
        }
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future future = executor.submit(new Task());

        try {
            Object object = future.get();
            System.out.println(object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
