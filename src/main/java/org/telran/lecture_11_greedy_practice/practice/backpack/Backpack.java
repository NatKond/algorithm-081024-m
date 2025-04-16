package org.telran.lecture_11_greedy_practice.practice.backpack;

// Есть набор предметов, у каждого предмета есть: цена и вес.
// Требуется выбрать из заданного набора предметов наиболее ценные, которые поместятся в рюкзак заданной вместимости.

// Алгоритм
// 1. Рассчитываем удельную ценность для каждого предмета.
// 2. Сортируем предметы по убыванию удельной ценности.
// 3. Складываем предметы в рюкзак(массив), по суммарный вес не превысит максимально допустимый


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Backpack {
    public static void main(String[] args) {
        List<Item> items = List.of( // Исходный набор предметов
                new Item("Sunscreen", 2, 3),
                new Item("Travel Towel", 4, 8),
                new Item("Camera", 6, 10),
                new Item("Guide Book", 3, 5),
                new Item("Swimsuit", 5, 9)
        );
        int totalWeight = 10; // Максимальная вместимость рюкзака
        List<Item> backpack = selectItems(items, totalWeight);
        System.out.println(backpack);
    }

    public static List<Item> selectItems(List<Item> allItems, int totalWeight) {

        if (allItems == null || allItems.isEmpty()) {
            throw new IndexOutOfBoundsException("Invalid data.");
        }

        List<Item> allItemsCopy = new ArrayList<>(allItems);

        allItemsCopy.sort(Comparator.comparingInt((Item i) -> -i.unit_value).thenComparing((Item i) -> i.weight));

        List<Item> selectedItems = new ArrayList<>();
        Item currentItem = allItemsCopy.get(0);
        int i = 1;
        while (totalWeight >= currentItem.weight) {
            totalWeight -= currentItem.weight;
            selectedItems.add(currentItem);
            currentItem = allItemsCopy.get(i++);
        }
        return selectedItems;
    }
}
