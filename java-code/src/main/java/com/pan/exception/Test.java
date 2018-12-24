package com.pan.exception;

/**
 * @author: panxiwen
 * @Description: TODO
 * @date 2018/12/24
 */
public class Test {

    public static void main(String[] args) {

        try {

            try {
                throw new NullPointerException();
            } catch (Exception e) {
                throw new MyException("exception:xx", e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
