package org.telran.lecture_06_quick.tasks;

// "Призеры олимпиады"
// По результатам олимпиады участники награждаются дипломами.
// Набравшие одинаковые баллы получают дипломы одинаковой степени.
// Призером олимпиады считается участник, получивший диплом не хуже III степени.
// По результатам олимпиады определите количество призеров.
// Вход: массив из N натуральных чисел – результаты участников.
// Выход: одно число – число призеров.

// Пример:
// Вход
// 1 3 4 3 5 6 7 7 6 1
// Выход
// 5

import java.util.Arrays;
import java.util.Comparator;

public class Task03 {
    public static void main(String[] args) {
        Integer[] results = {1, 3, 4, 3, 5, 6, 7, 7, 6, 1, 5, 5};
        System.out.println(countWinners(results));
    }

    public static int countWinners(Integer[] results) {
        if (results.length == 0) return 0;
        Arrays.sort(results, Comparator.reverseOrder());
        System.out.println("results = " + Arrays.toString(results));
        int count = 0;
        int degree = 1;
        for (int i = 0; i < results.length; i++) {
            count++;
            if (results[i] - results[i + 1] != 0 && (++degree > 3)) {
                break;
            }
        }
        return count;
    }
}
