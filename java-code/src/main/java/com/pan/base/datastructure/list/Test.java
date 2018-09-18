package com.pan.base.datastructure.list;

import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        MyList<String> myList = new MyArrayList<>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("3");
        myList.add("2");
        myList.add("1");
        myList.add("14");

        Iterator<String> iterator = myList.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
            System.out.println(data);
        }
        //查看扩容
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");
        myList.add("1");

        Iterator<String> iterator2 = myList.iterator();
        while (iterator2.hasNext()) {
            String data = iterator2.next();
            System.out.println(data);
        }
    }
}
