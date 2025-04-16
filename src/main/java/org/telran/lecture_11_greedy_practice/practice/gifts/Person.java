package org.telran.lecture_11_greedy_practice.practice.gifts;

import java.util.List;

public class Person {
    String name;
    List<String> preferences;

    public Person(String name, List<String> preferences) {
        this.name = name;
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return name + ", preferences=" + preferences;
    }
}
