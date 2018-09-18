package com.pan.base.pattern;

public class Test {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};


        int[] res = twoSum(nums, 9);

        System.out.println(res[0]+" *** " + res[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        System.out.println(nums.length);
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
