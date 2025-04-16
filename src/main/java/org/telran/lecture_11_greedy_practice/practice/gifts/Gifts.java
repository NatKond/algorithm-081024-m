package org.telran.lecture_11_greedy_practice.practice.gifts;

// У вас есть список людей, которым нужно подарить подарки.
// У каждого человека есть предпочтения по подаркам, выраженные в виде списка желаемых подарков.
// Каждый подарок можно подарить только одному человеку. Ваша задача - осчастливить как можно больше людей.


import java.util.*;

public class Gifts {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(
                List.of(
                        new Person("Alex", List.of("Watch", "Notebook", "Backpack")),
                        new Person("Bob", List.of("Keyboard", "Phone","Backpack")),
                        new Person("Mike", List.of("Book")),
                        new Person("Anna", List.of("Backpack","Watch"))
                )
        );

        List<String> gifts = List.of("Watch", "Apple", "Backpack", "Book", "Notebook");

        Map<String, Person> giftsToPerson = selectGiftsToPerson(people, gifts);

        System.out.println(people);

        System.out.println(gifts);

        System.out.println(giftsToPerson);
    }

    public static Map<String, Person> selectGiftsToPerson(List<Person> people, List<String> gifts) {
        List<Person> peopleCopy = new ArrayList<>(people);
        peopleCopy.sort(Comparator.comparingInt(p -> p.preferences.size()));
        Map<String, Person> giftsToPerson = new HashMap<>();
        for (Person person : peopleCopy) {
            for (String preference : person.preferences) {
                if (gifts.contains(preference)) {
                    giftsToPerson.put(preference, person);
                    break;
                }
            }
        }
        return giftsToPerson;
    }
}
