

package com.pan.enum_;

/**
 * Created by pan on 2017/10/15.
 */
public class EnumTest {

    public enum Apple {a, b, c}

    public static void main(String args[]) {

        Apple a = Apple.a;

        switch (a) {
            case b: System.out.println("b");break;
            case a: System.out.println("a");break;
            case c: System.out.println("c");break;
        }

        String s = "xx" ;
        switch (s) {
            case "aa":
                System.out.println("aa");
            case "bb":
                System.out.println("bb");
            case "xx":
                System.out.println("xx");
        }
    }
}
