package org.telran.lecture_11_greedy_practice.practice.hw;

import java.util.ArrayList;
import java.util.List;

public class Lock {

    public static void main(String[] args) {
        int initialCombination = 2345;
        int rightCombination = 5432;

        System.out.println(findMinimumNumberOfTurnovers(initialCombination, rightCombination));
        System.out.println(findMinimumNumberOfTurnovers(1919, 0000));
    }

    public static int findMinimumNumberOfTurnovers(int initialCombination, int rightCombination) {

        if (initialCombination == rightCombination) {
            return 0;
        }
        List<Integer> initialCombinationList = new ArrayList<>();
        List<Integer> rightCombinationList = new ArrayList<>();
        int combination = (initialCombination == 0) ? rightCombination : initialCombination;
        while (combination != 0) {
            initialCombinationList.add(initialCombination % 10);
            rightCombinationList.add(rightCombination % 10);
            combination /= 10;
            initialCombination /= 10;
            rightCombination /= 10;
        }

        int countTurnovers = 0;
        for (int i = 0; i < initialCombinationList.size(); i++) {
            int currentNumberInitial = initialCombinationList.get(i);
            int currentNumberRight = rightCombinationList.get(i);
            int difference = Math.abs(currentNumberInitial - currentNumberRight);
            countTurnovers += (difference <= 5) ? difference :
                            (currentNumberInitial < currentNumberRight) ?
                                    currentNumberInitial + (10 - currentNumberRight) :
                                    (10 - currentNumberInitial) + currentNumberRight;
        }

        /* int countTurnovers = 0;
        while (initialCombination != 0 && rightCombination != 0) {
            int currentNumberInitial = initialCombination % 10;
            int currentNumberRight = rightCombination % 10;
            countTurnovers +=
                    (Math.abs(currentNumberInitial - currentNumberRight) <= 5) ?
                            Math.abs(currentNumberInitial - currentNumberRight) :
                            currentNumberInitial + (10 - currentNumberRight);
            initialCombination /= 10;
            rightCombination /= 10;
        }*/
        return countTurnovers;
    }
}
