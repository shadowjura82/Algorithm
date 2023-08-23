package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr1 = new Integer[100000];
        for (int i = 0; i < arr1.length; i++)
            arr1[i] = (int) (Math.random() * 1000);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);

        long start = System.currentTimeMillis();
        sortBubble(arr1);
        System.out.println("Пузырьковый метод отсортировал за " + (System.currentTimeMillis() - start) + " мс");
        start = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println("Метод выборки отсортировал за " + (System.currentTimeMillis() - start) + " мс");
        start = System.currentTimeMillis();
        sortInsertion(arr3);
        System.out.println("Метод вставки отсортировал за " + (System.currentTimeMillis() - start) + " мс");
        start = System.currentTimeMillis();
        quickSort(arr4, 0, arr1.length - 1);
        System.out.println("Рекурсивный метод отсортировал за " + (System.currentTimeMillis() - start) + " мс");
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) swapElements(arr, j, j + 1);
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++)
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            swapElements(arr, i, minIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}