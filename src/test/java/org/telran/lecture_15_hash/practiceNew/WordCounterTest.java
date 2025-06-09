package org.telran.lecture_15_hash.practiceNew;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {
    @Test
    public void testSingleWord() {
        CommonHashTable table = callProcess("hello");
        assertEquals(1, table.get("hello"));
        assertEquals(1, table.size());
    }

    @Test
    public void testMultipleWords() {
        CommonHashTable table = callProcess("hello world hello");
        assertEquals(2, table.get("hello"));
        assertEquals(1, table.get("world"));
        assertEquals(2, table.size());
    }

    @Test
    public void testCaseInsensitive() {
        CommonHashTable table = callProcess("Hello hEllo HELLO");
        assertEquals(3, table.get("hello"));
        assertEquals(1, table.size());
    }

    @Test
    public void testEmptyString() {
        CommonHashTable table = callProcess("");
        assertEquals(0, table.size());
    }

    @Test
    public void testSpacesAndTabs() {
        CommonHashTable table = callProcess("hi     hi\thi");
        assertEquals(3, table.get("hi"));
        assertEquals(1, table.size());
    }

    @Test
    public void testUniqueWords() {
        CommonHashTable table = callProcess("one two three");
        assertEquals(1, table.get("one"));
        assertEquals(1, table.get("two"));
        assertEquals(1, table.get("three"));
        assertEquals(3, table.size());
    }

    @Test
    public void testComplexSentence() {
        CommonHashTable table = callProcess("Java is great and Java is powerful and great");
        assertEquals(2, table.get("java"));
        assertEquals(2, table.get("is"));
        assertEquals(2, table.get("great"));
        assertEquals(2, table.get("and"));
        assertEquals(1, table.get("powerful"));
        assertEquals(5, table.size());
    }

    private CommonHashTable callProcess(String sentence) {
        return WordCounter.process(sentence);
    }
}