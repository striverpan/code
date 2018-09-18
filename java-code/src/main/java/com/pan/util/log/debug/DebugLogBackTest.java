package com.pan.util.log.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pan on 2018/1/22.
 */
public class DebugLogBackTest {

    private static Logger logger = LoggerFactory.getLogger(DebugLogBackTest.class);

    public static void main(String[] args) {
        logger.debug("debug",new Exception());
    }
}
