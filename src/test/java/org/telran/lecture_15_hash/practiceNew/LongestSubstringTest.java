package org.telran.lecture_15_hash.practiceNew;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringTest {

    @Test
    void testGeneralCase() {
        String result = LongestSubstring.getMaxSubstring("abcdecfg1");
        assertEquals("decfg1", result);
    }

    @Test
    void testAllSameCharacters() {
        String result = LongestSubstring.getMaxSubstring("aaaa");
        assertEquals("a", result);
    }

    @Test
    void testEmptyString() {
        String result = LongestSubstring.getMaxSubstring("");
        assertEquals("", result);
    }

    @Test
    void testNullInput() {
        String result = LongestSubstring.getMaxSubstring(null);
        assertEquals("", result);
    }

    @Test
    void testAllUniqueCharacters() {
        String result = LongestSubstring.getMaxSubstring("abcdef");
        assertEquals("abcdef", result);
    }

    @Test
    void testEndsWithLongestSubstring() {
        String result = LongestSubstring.getMaxSubstring("aaabcdef");
        assertEquals("abcdef", result);
    }
}