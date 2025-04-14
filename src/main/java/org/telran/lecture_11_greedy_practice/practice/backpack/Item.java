package org.telran.lecture_11_greedy_practice.practice.backpack;

public class Item {
    String name;
    int weight;
    int value;
    int unit_value; // Удельная ценность - цена за единицу веса

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.unit_value = value * 100/weight;
    }
}
