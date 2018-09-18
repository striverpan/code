package com.pan.util.log;


import org.apache.log4j.Logger;

/**
 * Created by pan on 2018/1/21.
 */
public class Log4jTest {

    private static Logger logger = Logger.getLogger(Log4jTest.class);

    public static void mathTest(int a,int b){
        try {
            int c = a/b;
        }catch (Exception e){
            logger.info("error open ",e);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            logger.info("log start " + i);
            mathTest(i,0);
            logger.info("log end "+i);
        }
    }
}
