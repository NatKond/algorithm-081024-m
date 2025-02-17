package org.telran.lecture_03_recursion.practice;

// Задача:
//  Дано целое положительно число n.
//  Выведите все целые числа в порядке убывания от n до -n.
// Напишите рекурсивное решение

public class Countdown {
    public static void main(String[] args) {
        countdown(3); // Выведет: 3 2 1 0 -1 -2 -3
        countdown(5); // Выведет: 5 4 3 2 1 0 -1 -2 -3 -4 -5
    }

    public static void countdown(int n) {
        countdownRecursion(n, -n);
        System.out.println();
    }

    private static void countdownRecursion(int n, int bound) {
        System.out.print(n + " ");
        if (n > bound) {
            countdownRecursion(n - 1, bound);
        }
    }
}
