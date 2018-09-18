package com.pan.util.log;

import com.pan.util.log.debug.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pan on 2018/1/22.
 */
public class LogBackTest {

    private static Logger logger = LoggerFactory.getLogger(LogBackTest.class);

    public static void test() {

        try {
            //throw new NullPointerException();
            int a=10/0;
        } catch (Exception e) {
            throw new MyException("错误发生", e);
        }
    }

    public static void main(String[] args) {
        //logger.info("info", new Exception());
        try {
            test();
        } catch (MyException e) {
            logger.error("The MyException occurred", e);
        }

        //  logger.debug("debug", new Exception());
    }
}
