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
        Item[] items = new Item[]{ // Исходный набор предметов
                new Item("Sunscreen", 2, 3),
                new Item("Travel Towel", 4, 8),
                new Item("Camera", 6, 10),
                new Item("Guide Book", 3, 5),
                new Item("Swimsuit", 5, 9)
        };
        int totalWeight = 10; // Максимальная вместимость рюкзака
    }

    public static List<Item> selectItems(List<Item> allItems, int totalWeight) {

        if (allItems == null || allItems.isEmpty()) {
            throw new IndexOutOfBoundsException("Invalid data.");
        }

        List<Item> allItemsCopy = new ArrayList<>(allItems);

        allItemsCopy.sort((i1, i2) -> Integer.compare(i2.unit_value, i1.unit_value));

        List<Item> selectedItems = new ArrayList<>();
        Item currentItem = allItemsCopy.getFirst();
        int i = 1;
        while (totalWeight >= currentItem.weight) {
            totalWeight -= currentItem.weight;
            selectedItems.add(currentItem);
            currentItem = allItemsCopy.get(i++);
        }
        return selectedItems;
    }

}
