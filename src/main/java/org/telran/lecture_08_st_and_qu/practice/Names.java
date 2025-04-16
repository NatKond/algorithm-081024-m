package org.telran.lecture_08_st_and_qu.practice;

// Дан массив имен.
// Найдите самое длинное имя, если таких имен несколько - выведи любое их них

import java.util.Arrays;
import java.util.Comparator;

public class Names {

    public static void main(String[] args) {
        String[] names = {"Olga", "Anna", "Anton", "Peter", "Alexander"};
        System.out.println(findLongestName(names));
    }

    public static String findLongestName(String[] names){
        String[] namesCopy = Arrays.copyOf(names, names.length);
        Arrays.sort(namesCopy, Comparator.comparingInt(String::length).reversed());
        return namesCopy[0];
    }
}
