package org.telran.lecture_05_merge.practice.tasks;

import java.util.Arrays;
import java.util.Scanner;

// "Сумма с условием"
// Дан массив произвольных целых чисел
// Найти: сумму элементов списка, больших данного числа a. Значение a можно задать самостоятельно.
// Примечание: Используйте алгоритм сортировки, если нужно.

public class Task02 {
    public static void main(String[] args) {
        int[] numbers = {2, 4, 8, -5, 0, 7, 12, 0, -4, 10};
        int a = 0;
        System.out.println("sumElementsBiggerThan(numbers,14) = " + sumElementsBiggerThan(numbers, 5));
    }

    public static int sumElementsBiggerThan(int[] numbers, int target) {
        int sum = 0;
        for (int number : numbers) {
            if (number > target) {
                sum += number;
            }
        }
        return sum;
    }

    public static int sumElementsBiggerThanWithSort(int[] numbers, int target) {
        Arrays.sort(numbers);
        if (target >= numbers[numbers.length - 1]) {
            return 0;
        }
        int targetIndex = 0;
        int left = 0, right = numbers.length - 1;
        while (numbers[left] < numbers[right]) {
            int middle = (left + right) / 2;
            if (numbers[middle] == target) {
                targetIndex = middle + 1;
                break;
            }
            if (numbers[middle] > target) {
                if (numbers[middle - 1] < target) {
                    targetIndex = middle;
                    break;
                }
                right = middle - 1;
            }
            if (numbers[middle] < target) {
                if (numbers[middle + 1] > target) {
                    targetIndex = middle + 1;
                    break;
                }
                left = middle + 1;
            }
        }
        int sum = 0;
        for (int i = targetIndex; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
