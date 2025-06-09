package org.telran.lecture_15_hash.practiceNew;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonHashTableTest {
    private CommonHashTable table;

    @BeforeEach
    void setUp() {
        table = new CommonHashTable(4);
    }

    @Test
    void testPutAndGet() {
        table.put("key1", "value1");
        table.put("key2", "value2");

        assertEquals("value1", table.get("key1"));
        assertEquals("value2", table.get("key2"));
        assertNull(table.get("key3"));
    }

    @Test
    void testUpdateValue() {
        table.put("key", "oldValue");
        table.put("key", "newValue");

        assertEquals("newValue", table.get("key"));
    }

    @Test
    void testRemove() {
        table.put("key", "value");
        assertEquals("value", table.remove("key"));
        assertNull(table.get("key"));
        assertFalse(table.containsKey("key"));
    }

    @Test
    void testRemoveNonExistent() {
        assertNull(table.remove("no-such-key"));
    }

    @Test
    void testContainsKey() {
        table.put("x", 123);
        assertTrue(table.containsKey("x"));
        assertFalse(table.containsKey("y"));
    }

    @Test
    void testSizeAndIsEmpty() {
        assertTrue(table.isEmpty());

        table.put("a", 1);
        table.put("b", 2);
        assertEquals(2, table.size());
        assertFalse(table.isEmpty());

        table.remove("a");
        assertEquals(1, table.size());
    }

    @Test
    void testClear() {
        table.put("a", 1);
        table.put("b", 2);
        table.clear();

        assertEquals(0, table.size());
        assertNull(table.get("a"));
        assertTrue(table.isEmpty());
    }

    @Test
    void testCollisionHandling() {
        // Эти числа при capacity=4 попадут в одну корзину
        table.put(0, "Zero");
        table.put(4, "Four");
        table.put(8, "Eight");

        assertEquals("Zero", table.get(0));
        assertEquals("Four", table.get(4));
        assertEquals("Eight", table.get(8));
    }

    @Test
    void testStringKeyHashCollisions() {
        table.put("ab", "AB");
        table.put("ba", "BA");

        assertEquals("AB", table.get("ab"));
        assertEquals("BA", table.get("ba"));
    }

    @Test
    void testResizeOnLoadFactor() {
        // Изначальная capacity = 4, LOAD_FACTOR = 0.75
        table.put("a", "A"); // 1
        table.put("b", "B"); // 2
        table.put("c", "C"); // 3 -> должно сработать resize при следующем

        table.put("d", "D"); // resize

        assertEquals("A", table.get("a"));
        assertEquals("B", table.get("b"));
        assertEquals("C", table.get("c"));
        assertEquals("D", table.get("d"));
        assertEquals(4, table.size());
    }

    @Test
    void testIntegerKeys() {
        table.put(1, "one");
        table.put(2, "two");
        table.put(3, "three");

        assertEquals("one", table.get(1));
        assertEquals("two", table.get(2));
        assertEquals("three", table.get(3));
    }

    @Test
    void testRehashPreservesValues() {
        for (int i = 0; i < 20; i++) {
            table.put(i, "val" + i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("val" + i, table.get(i));
        }
    }

    @Test
    void testValuesEmptyTable() {
        Object[] values = table.values();
        assertEquals(0, values.length);
    }

    @Test
    void testValuesSingleEntry() {
        table.put("one", 1);
        Object[] values = table.values();
        assertArrayEquals(new Object[]{1}, values);
    }

    @Test
    void testValuesMultipleEntriesNoCollisions() {
        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);

        Object[] values = table.values();
        assertEquals(3, values.length);
        assertTrue(containsAll(values, 1, 2, 3));
    }

    @Test
    void testValuesWithCollisions() {
        // Принудим коллизии вручную
        table = new CommonHashTable(2);
        table.put(0, "Zero");
        table.put(2, "Two");
        table.put(4, "Four");

        Object[] values = table.values();
        assertEquals(3, values.length);
        assertTrue(containsAll(values, "Zero", "Two", "Four"));
    }

    private boolean containsAll(Object[] values, Object... expected) {
        for (Object e : expected) {
            boolean found = false;
            for (Object v : values) {
                if (e.equals(v)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

}