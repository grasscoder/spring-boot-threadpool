package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 1));
        System.out.println((1 << 31) - 1);
        System.out.println(-(1 << 31));
        //System.out.println(Math.pow(11, 2));
        System.out.println(pow(10));
    }

    static long pow(int k){
        int init = 10;
        if(k == 1){
            return init;
        }
        return init*pow(k-1);
    }
    public static int minIndex(int[] nums, int target) {
        int index = binarySearch(nums, 0, nums.length - 1, target);
        if (index == -1) {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
        }
        return 0;
    }

    public static int binarySearch(int[] nums, int start, int end, int target) {
        if (start > end)
            return -1;
        int i = start;
        int j = end;
        int mid = (i + j) / 2;
        if (nums[mid] == target) {
            if (mid == 0) {
                return mid;
            } else {
                int s = mid;
                while (s > 0 && nums[s - 1] == nums[s]) {
                    s--;
                }
                if (s == 0 && nums[0] == nums[1]) {
                    return 0;
                } else if (s == 0 && nums[0] != nums[1]) {
                    return 1;
                }
                return s;
            }
        } else if (nums[mid] > target) {
            return binarySearch(nums, start, mid - 1, target);
        } else {
            return binarySearch(nums, mid + 1, end, target);
        }
    }
}
