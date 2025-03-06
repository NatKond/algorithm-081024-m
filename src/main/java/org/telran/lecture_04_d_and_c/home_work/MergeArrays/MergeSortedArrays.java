package org.telran.lecture_04_d_and_c.home_work.MergeArrays;

// Задача: "Объединение отсортированных массивов"
// Даны два целочисленных массива arr1 и arr2, каждый из которых отсортирован по возрастанию.
// Необходимо создать новый целочисленный массив mergedArray,
// который будет содержать все элементы массивов arr1 и arr2 в отсортированном порядке (также по возрастанию).

// Входные данные:
// arr1: Целочисленный массив, отсортированный по возрастанию.
// arr2: Целочисленный массив, отсортированный по возрастанию.

// Выходные данные:
// mergedArray: Целочисленный массив, содержащий все элементы arr1 и arr2 в отсортированном порядке по возрастанию.

// Ограничения:
// Массивы arr1 и arr2 могут быть пустыми.
// Элементы массивов arr1 и arr2 могут повторяться.

// Примеры:
//Вход: arr1 = [1, 3, 5, 7], arr2 = [2, 4, 6, 8]
//Выход: mergedArray = [1, 2, 3, 4, 5, 6, 7, 8]

//Вход: arr1 = [1, 2, 2, 5], arr2 = [1, 3, 4]
//Выход: mergedArray = [1, 1, 2, 2, 3, 4, 5]

//Вход: arr1 = [], arr2 = [1, 2, 3]
//Выход: mergedArray = [1, 2, 3]

public class MergeSortedArrays {

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] mergedArray = new int[arr1.length + arr2.length];
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < mergedArray.length; i++) {
            if (index1 < arr1.length && (index2 == arr2.length || arr1[index1] < arr2[index2])) {
                mergedArray[i] = arr1[index1++];
            } else {
                mergedArray[i] = arr2[index2++];
            }
        }
        return mergedArray;
    }

    public static int[] mergeSortedArraysWhile(int[] arr1, int[] arr2) {
        int[] mergedArray = new int[arr1.length + arr2.length];
        int index1 = 0;
        int index2 = 0;
        int indexMerged = 0;
        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] < arr2[index2]) {
                mergedArray[indexMerged++] = arr1[index1++];
            } else {
                mergedArray[indexMerged++] = arr2[index2++];
            }
        }
        while (indexMerged < mergedArray.length) {
            if (index1 < arr1.length) {
                mergedArray[indexMerged++] = arr1[index1++];
            }
            if (index2 < arr2.length){
                mergedArray[indexMerged++] = arr2[index2++];
            }
        }
        return mergedArray;
    }

    public static void main(String[] args) {
//      int[] arr1 = {1, 3, 5, 7};
//      int[] arr2 = {2, 4, 6, 8};
        int[] arr1 = {1, 2, 2, 5};
        int[] arr2 = {1, 3, 4};
        int[] mergedArray = mergeSortedArrays(arr1, arr2);
        int[] mergedArray2 = mergeSortedArraysWhile(arr1,arr2);

        System.out.print("Объединенный массив: ");
        for (int num : mergedArray2) {
            System.out.print(num + " ");
        }
    }
}
