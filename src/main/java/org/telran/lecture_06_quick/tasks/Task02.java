package org.telran.lecture_06_quick.tasks;

import java.util.Arrays;
import java.util.Comparator;

// "Сумма наибольших по модулю"
// Дан массив чисел.
// Найти: сумму 5-ти самых больших элементов по модулю.
// Пояснение: сравниваем элементы по модулю, а в сумму добавляем сами значения элементов(без модуля)
// В примере ниже, два самых больших по модулю числа это: -224 и 211. Они самые большие по модулю, а их сумма = -13

public class Task02 {
    public static void main(String[] args) {
        Integer[] numbers = {-25, 136, -13, -224, -128, -67, 128, -21, 4, 211, 0};
        System.out.println("sumFiveMaxAbsVal(numbers) = " + sumFiveMaxAbsVal(numbers));
    }

    public static int sumFiveMaxAbsVal(Integer[] array) {
        Arrays.sort(array, Comparator.comparing(Math::abs, Comparator.reverseOrder()));
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += array[i];
        }
        return sum;
    }
}
