package org.telran.lecture_04_d_and_c.practice.BinarySearch;
// Алгоритм "Бинарный поиск"
// 1. Начинаем с поиска среднего элемента массива.
// 2. Если средний элемент совпадает с искомым, поиск завершается.
// 3. Если искомый элемент меньше среднего, поиск продолжается в левой половине массива.
// 4. Если искомый элемент больше среднего, поиск продолжается в правой половине массива.
// 5. Шаги 1-4 повторяются до тех пор, пока элемент не будет найден или пока не останется пустой диапазон для поиска.

public class BinSearch {

    public static void main(String[] args) {

        int[] arr1 = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int target = 2;
        int result = binarySearchRecursive(arr1, target, 0, arr1.length - 1);

        if (result == -1) {
            System.out.println("Элемент не найден");
        } else {
            System.out.println("Элемент найден по индексу " + result);
        }

    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (right - left >= 0) {
            int middle = (left + right) / 2;
            if (arr[middle] == target) {
                return middle;
            }
            if (target < arr[middle]) {
                return binarySearchRecursive(arr, target, left, middle - 1);
            } else {
                return binarySearchRecursive(arr, target, middle + 1, right);
            }
        }
        return -1;
    }
}