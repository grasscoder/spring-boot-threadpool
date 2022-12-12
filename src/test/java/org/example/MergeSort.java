package org.example;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 3, 1, 6, 8, 7, 10, 9};
        //mergeSort(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(arr, 0, arr.length - 1, 3));
    }

    public static void mergeSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int[] tmpArr = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tmpArr[k++] = arr[i++];
            } else {
                tmpArr[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmpArr[k++] = arr[i++];
        }
        while (j <= end) {
            tmpArr[k++] = arr[j++];
        }
        for (k = 0; k < tmpArr.length; k++) {
            arr[start + k] = tmpArr[k];
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int i = start;
        int j = end;
        int povit = arr[j];
        while (i < j) {
            while (i < j && arr[i] <= povit) {
                i++;
            }
            arr[j] = arr[i];
            while (i < j && arr[j] > povit) {
                j--;
            }
            arr[i] = arr[j];
        }
        arr[i] = povit;
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }

    public static int binarySearch(int[] arr, int start, int end, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int i = start;
        int j = end;
        while (i < j) {
            int mid = (i + j) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return -1;
    }
}
