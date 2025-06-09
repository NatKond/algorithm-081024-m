package org.telran.lecture_15_hash.practiceNew;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniqueBookStoreTest {

    private UniqueBookStore library;

    @BeforeEach
    void setUp() {
        library = new UniqueBookStore(4);
    }

    @Test
    void testAddNewBook() {
        assertTrue(library.add("1984"));
        assertEquals(1, library.size());
        assertTrue(library.contains("1984"));
    }

    @Test
    void testAddDuplicateBook() {
        assertTrue(library.add("1984"));
        assertFalse(library.add("1984")); // дубликат
        assertEquals(1, library.size());
    }

    @Test
    void testContainsBook() {
        library.add("Мастер и Маргарита");
        assertTrue(library.contains("Мастер и Маргарита"));
        assertFalse(library.contains("Преступление и наказание"));
    }

    @Test
    void testRemoveExistingBook() {
        library.add("Война и мир");
        assertNotNull(library.remove("Война и мир"));
        assertFalse(library.contains("Война и мир"));
        assertEquals(0, library.size());
    }

    @Test
    void testRemoveNonExistingBook() {
        library.add("1984");
        assertNull(library.remove("Преступление и наказание"));
        assertEquals(1, library.size());
    }

    @Test
    void testRehashing() {
        // Изначально capacity = 4, threshold = 3
        assertTrue(library.add("Book1"));
        assertTrue(library.add("Book2"));
        assertTrue(library.add("Book3"));
        assertEquals(3, library.size());

        // добавление ещё одной книги вызовет rehash()
        assertTrue(library.add("Book4"));
        assertEquals(4, library.size());

        // все книги должны остаться доступными
        assertTrue(library.contains("Book1"));
        assertTrue(library.contains("Book2"));
        assertTrue(library.contains("Book3"));
        assertTrue(library.contains("Book4"));
    }

    @Test
    void testChainCollisionHandling() {
        // Принудительно создаём коллизии
        // Используем строки с одинаковым хешем (если возможно) — либо просто добавляем вручную
        library.add("AaAa"); // Эти строки могут давать одинаковые хеши
        library.add("BBBB");

        assertTrue(library.contains("AaAa"));
        assertTrue(library.contains("BBBB"));
        assertEquals(2, library.size());
    }

    @Test
    void testAddNullBook() {
        // null не поддерживается в реализации
        assertThrows(NullPointerException.class, () -> library.add(null));
    }

    @Test
    void testRemoveFromEmptyLibrary() {
        assertNull(library.remove("Неизвестная книга"));
    }

    @Test
    void testSizeAfterAddRemove() {
        library.add("A");
        library.add("B");
        library.add("C");
        assertEquals(3, library.size());

        library.remove("B");
        assertEquals(2, library.size());
    }

    @Test
    void testAddManyBooks() {
        for (int i = 0; i < 100; i++) {
            assertTrue(library.add("Book" + i));
        }
        assertEquals(100, library.size());
    }
}