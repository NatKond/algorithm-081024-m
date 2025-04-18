package org.telran.lecture_08_st_and_qu.practice;

// В магазине продают только по три и по пять воздушных шариков.
// Можно ли купить ровно k шариков?

// Пример 1:
// Input: 8
// Output: Yes
// Пояснение: Можно купить 5 + 3 шарика

// Пример 2:
// Input: 10
// Output: Yes
// Пояснение: Можно купить 5 + 5 шариков

// Пример 3:
// Input: 7
// Output: No
// Пояснение: Никакими комбинациями 5 и 3, 7 шариков купить невозможно

public class Balloons {

    public static void main(String[] args) {
        System.out.println(isPossibleToBuy(8, new int[]{5, 3}));
        System.out.println(isPossibleToBuy(10, new int[]{5, 3}));
        System.out.println(isPossibleToBuy(7, new int[]{5, 3}));
    }

    public static boolean isPossibleToBuy(int countBalloons, int[] quantity) {
        int index = 0;
        while (countBalloons > 0) {
            if (countBalloons >= quantity[index]) {
                countBalloons -= quantity[index];
            } else {
                index++;
                if (index == quantity.length){
                    break;
                }
            }
            if (countBalloons == 0){
                return true;
            }
        }
        return false;
    }
}
