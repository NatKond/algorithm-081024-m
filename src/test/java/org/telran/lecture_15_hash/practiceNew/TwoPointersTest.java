package org.telran.lecture_15_hash.practiceNew;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoPointersTest {

    TwoPointers tp = new TwoPointers();

    @Test
    void testTwoSumSorted_found() {
        int[] result = tp.twoSumSorted(new int[]{1, 2, 4, 6, 10}, 8);
        assertArrayEquals(new int[]{2, 6}, result);
    }

    @Test
    void testTwoSumSorted_notFound() {
        int[] result = tp.twoSumSorted(new int[]{1, 2, 3, 9}, 8);
        assertArrayEquals(new int[]{}, result);
    }

    @Test
    void testSortArrayByParity_allEven() {
        int[] result = tp.sortArrayByParity(new int[]{2, 4, 6});
        assertTrue(allEvenFirst(result));
    }

    @Test
    void testSortArrayByParity_mixed() {
        int[] result = tp.sortArrayByParity(new int[]{3, 1, 2, 4});
        assertTrue(allEvenFirst(result));
    }

    @Test
    void testSortArrayByParity_allOdd() {
        int[] result = tp.sortArrayByParity(new int[]{1, 3, 5});
        assertTrue(allEvenFirst(result));
    }

    private boolean allEvenFirst(int[] arr) {
        boolean foundOdd = false;
        for (int num : arr) {
            if (num % 2 == 0) {
                if (foundOdd) return false;
            } else {
                foundOdd = true;
            }
        }
        return true;
    }
}