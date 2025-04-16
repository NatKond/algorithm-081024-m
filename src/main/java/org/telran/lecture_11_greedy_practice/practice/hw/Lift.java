package org.telran.lecture_11_greedy_practice.practice.hw;

// Задача: вызов лифта
// В доме n лифтов, у каждого лифта своя скорость, измеряемая в сек/этаж (секунд на один этаж).
// Реализовать алгоритм отправки лифта, который приедет быстрее всего.
// Алгоритм будет применяться в домах разной этажности.
// Алгоритм будет применяться в домах с количеством > 2 лифтов
// Пример:
//  в доме 19 этажей
//  работают лифт А, лифт В и лифт C
//  лифт А находится на 4 этаже и движется со скоростью 3 сек/этаж.
//  лифт В находится на 8 этаже и движется со скоростью 2 сек/этаж.
//  лифт C находится на 5 этаже и движется со скоростью 2 сек/этаж.
//  На вход: 1 (вызываем на первый этаж)
//  результат: отправляем лифт С

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lift {
    String name;
    int level;
    int speed;

    public Lift(int speed, int level, String name) {
        this.speed = speed;
        this.level = level;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": level = " + level + ", speed = " + speed;
    }

    public static void main(String[] args) {
        List<Lift> lifts = List.of(
                new Lift(2, 10, "A"),
                new Lift(3, 15, "B"),
                new Lift(1, 5, "C"),
                new Lift(4, 3, "D"),
                new Lift(2, 8, "E")
        );

        int floor = 1;
        System.out.println(sendElevator(lifts, floor));

    }

    public static Lift sendElevator(List<Lift> lifts, int floor) {
        List<Lift> liftsCopy = new ArrayList<>(lifts);

        liftsCopy.sort(Comparator.comparingDouble((Lift l) -> Math.abs(floor - l.level) /(double) l.speed));

        return liftsCopy.get(0);
    }
}
