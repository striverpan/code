package com.pan;

/**
 * Created by pan on 2017/10/15.
 */
public class Test {

    public static void main(String args[]) {
      String date = "20180819";
      String newdate = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
        System.out.println(newdate);
    }
}

