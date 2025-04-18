package org.telran.lecture_01_intro.practice;

// Задача: написать программу, для поиска заданного числа в массиве.
// Если число найдено - вывести "Найдено значение ...", если нет - вывести "Значение ... не найдено"

// Алгоритм
// Шаг 1: Получить массив и искомое число
// Шаг 2: Начните с крайнего левого элемента массива и один за другим сравните искомое число с каждым элементом массива
// Шаг 3: Если искомое число соответствует элементу массива, вывести "Найдено" и завершить перебор элементов.
// Шаг 4: Если искомое число не совпало ни с одним из элементов, выведите "Не найдено".

public class LinearSearch {
    public static void main(String[] args) {
        int[] numbers = {2, 7, -4, 4, 3, 0, 5, 10};
        int target_number = 5;
        // FIXED!
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target_number) {
                System.out.println("Найдено значение " + target_number);
                break;
            } else if (i == numbers.length - 1) {
                System.out.println("Значение " + target_number + " не найдено");
            }
        }
    }
}
