package com.pan.base.datastructure.string;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: panxiwen
 * @Description: TODO
 * @date 2018/12/23
 */
public class TT {

    public static void main(String[] args) {


        ArrayList<String> set=new ArrayList<String>();

        set.add("ca");
        set.add("bbb");
        set.add("11");
        Collections.sort(set);
        for(String str:set){
            System.out.println(str);
        }

    }
}
