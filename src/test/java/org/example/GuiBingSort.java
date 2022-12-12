package org.example;

import java.util.Arrays;

public class GuiBingSort {
    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 3, 5, 7, 9};
        //mergeSort(arr, 0, arr.length - 1);
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
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
        if (start >= end) {
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
}
