package com.pan.base.datastructure.string;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author: panxiwen
 * @Description: TODO
 * @date 2018/12/23
 */

//华为string机试题
public class HW {

    public static void main(String[] args) {


        Scanner inScanner = new Scanner(System.in);
        while (inScanner.hasNextLine()) {
            String inputStr = inScanner.nextLine().trim();
            char[] expression = inputStr.toCharArray();
            char[] newExpression = new char[expression.length];
            int newLength = 0;
            for (int i = 0; i < expression.length;) {

                if (expression[i] == '*') {
                    int preValue = expression[i - 1];
                    int postVlaue = expression[i + 1];
                    newExpression[i - 1] = (char) (preValue * postVlaue);
                    i = i + 2;
                    newLength++;
                } else {
                    newExpression[i] = expression[i];
                    i++;
                    newLength++;
                }
            }

            Stack<Character> stack = new Stack();

            for (int j = newLength-1; j >= 0; j--) {
                stack.push(newExpression[j]);
            }


            Integer res = Integer.valueOf(stack.pop()-'0');

            while (!stack.isEmpty()) {

                Character operator = (Character) stack.pop();
                Integer postValue = Integer.valueOf(stack.pop()-'0');

                if (operator == '+') {
                    res = res + postValue;
                } else if (operator == '-') {
                    res = res - postValue;
                }
            }

            System.out.println(res);

        }

    }


}
