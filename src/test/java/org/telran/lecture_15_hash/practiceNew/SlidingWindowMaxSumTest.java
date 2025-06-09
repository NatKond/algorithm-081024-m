package org.telran.lecture_15_hash.practiceNew;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlidingWindowMaxSumTest {

    @Test
    public void testFindMaxSum() {
        int[] nums = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 4;
        assertEquals(24, SlidingWindowMaxSum.findMaxSum(nums, k));
    }

    @Test
    public void testFindMaxSumWithNegativeNumbers() {
        int[] nums = {-1, -2, -3, -4, -5};
        int k = 2;
        assertEquals(-3, SlidingWindowMaxSum.findMaxSum(nums, k));
    }

    @Test
    public void testFindMaxSumWithAllSameNumbers() {
        int[] nums = {2, 2, 2, 2, 2};
        int k = 3;
        assertEquals(6, SlidingWindowMaxSum.findMaxSum(nums, k));
    }

    @Test
    public void testFindMaxSumWithKEqualToArrayLength() {
        int[] nums = {1, 2, 3, 4};
        int k = 4;
        assertEquals(10, SlidingWindowMaxSum.findMaxSum(nums, k));
    }

    @Test
    public void testFindMaxSumWithInvalidInput() {
        int[] nums = {1, 2, 3};
        int k = 0;
        assertThrows(IllegalArgumentException.class, () -> SlidingWindowMaxSum.findMaxSum(nums, k));
    }
}