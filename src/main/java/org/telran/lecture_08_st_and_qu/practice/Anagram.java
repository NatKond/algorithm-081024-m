package org.telran.lecture_08_st_and_qu.practice;

// Даны две строки.
// Проверить, являются ли они анаграммами (состоят из одних и тех же букв, но в разном порядке).

import java.util.Arrays;

public class Anagram {

    public static void main(String[] args) {

        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("dusty", "study"));
        System.out.println(isAnagram("apple", "study"));

    }

    public static boolean isAnagram(String word1, String word2){
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();
        Arrays.sort(word1Array);
        Arrays.sort(word2Array);
        return Arrays.equals(word1Array, word2Array);
    }
}
