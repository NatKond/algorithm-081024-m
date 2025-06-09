package org.telran.lecture_15_hash.practiceNew;

/**
 * Класс для поиска максимальной суммы подмассива фиксированной длины k.
 */
public class SlidingWindowMaxSum {

    /**
     * Метод для поиска максимальной суммы подмассива длины k.
     *
     * @param nums массив целых чисел
     * @param k    длина подмассива
     * @return максимальная сумма подмассива длины k
     * @throws IllegalArgumentException если входные параметры некорректны
     */
    public static int findMaxSum(int[] nums, int k) {
        // Проверка на корректность входных данных
        // Если nums равен null, длина nums меньше k или k меньше либо равно 0,
        // то выбрасываем IllegalArgumentException с сообщением "Некорректные параметры"

        // Инициализация переменных maxSum и windowSum

        // 1. Вычисляем сумму первого окна из k элементов
        // Проходим по первым k элементам массива и суммируем их в windowSum
        // Устанавливаем maxSum равным windowSum

        // 2. Начинаем "скользить" по массиву, начиная с позиции k
        // Проходим по массиву, начиная с k-го элемента до конца
        // Удаляем элемент, выходящий из окна (nums[end - k])
        // Добавляем элемент, входящий в окно (nums[end])
        // Обновляем максимум, сравнивая текущий maxSum и windowSum

        // Возвращаем maxSum
        throw new UnsupportedOperationException("Metho findMaxSum is not implemented yet");
    }
}