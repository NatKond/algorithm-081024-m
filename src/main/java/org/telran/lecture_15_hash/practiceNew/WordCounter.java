package org.telran.lecture_15_hash.practiceNew;

import java.util.Arrays;

/**
 * Класс для подсчета количества вхождений слов в предложении.
 */
public class WordCounter {

    /**
     * Метод для обработки предложения и подсчета количества вхождений каждого слова.
     *
     * @param sentence предложение для обработки
     * @return хеш-таблица, содержащая слова и количество их вхождений
     */
    public static CommonHashTable process(String sentence) {

        CommonHashTable table = new CommonHashTable(); // word, count times this, 2

        String[] words = sentence.toLowerCase().replaceAll("\\s{2,}", " ").split("\\s");
        System.out.println("words = " + Arrays.toString(words));
        for (String word : words) {
            if (table.containsKey(word)) {
                int value = (int) table.get(word);
                table.put(word, value + 1);
            } else {
                table.put(word, 1);
            }
        }
        return table;
    }

    /**
     * Главный метод для демонстрации работы подсчета слов.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // Обработка предложения и вывод результата
        String text = "This is simple text and this is one and two the three hello world and";
        //"this" -> 2
        CommonHashTable table = process(text);
        table.printTable();
        System.out.println("word this = " + table.get("this"));

        System.out.println(12 == 12);

        String str1 = new String("12");
        String str2 = new String("12");
        System.out.println(str1.equals(str2));
    }
}
