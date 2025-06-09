package org.telran.lecture_14_hash.practiceNew;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable();
    }

    @Test
    @DisplayName("Пустая таблица должна иметь размер 0")
    void newTableShouldBeEmpty() {
        assertEquals(0, hashTable.size());
        assertTrue(hashTable.isEmpty());
    }

    @Test
    @DisplayName("Пустая таблица должна возвращать null при поиске по любому ключу")
    void emptyTableShouldReturnNullForAnyKey() {
        assertNull(hashTable.get(42));
        assertNull(hashTable.get(0));
        assertNull(hashTable.get(-1));
    }

    @Test
    @DisplayName("Конструктор с отрицательной емкостью должен бросать исключение")
    void constructorShouldThrowExceptionForNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new HashTable(-1));
        assertThrows(IllegalArgumentException.class, () -> new HashTable(0));
    }

    @Test
    @DisplayName("Проверка метода put и get для одиночного элемента")
    void putAndGetSingleElement() {
        hashTable.put(1, "Value1");

        assertEquals("Value1", hashTable.get(1));
        assertEquals(1, hashTable.size());
        assertFalse(hashTable.isEmpty());
    }

    @Test
    @DisplayName("Проверка метода put с обновлением существующего значения")
    void putShouldUpdateExistingValue() {
        hashTable.put(1, "Value1");
        hashTable.put(1, "UpdatedValue1");

        assertEquals("UpdatedValue1", hashTable.get(1));
        assertEquals(1, hashTable.size());
    }

    @Test
    @DisplayName("Проверка добавления нескольких элементов с разными ключами")
    void putAndGetMultipleElements() {
        hashTable.put(1, "Value1");
        hashTable.put(2, "Value2");
        hashTable.put(3, "Value3");

        assertEquals("Value1", hashTable.get(1));
        assertEquals("Value2", hashTable.get(2));
        assertEquals("Value3", hashTable.get(3));
        assertEquals(3, hashTable.size());
    }

    @Test
    @DisplayName("Проверка containsKey для существующих и несуществующих ключей")
    void containsKeyShouldReturnCorrectResult() {
        hashTable.put(1, "Value1");
        hashTable.put(2, "Value2");

        assertTrue(hashTable.containsKey(1));
        assertTrue(hashTable.containsKey(2));
        assertFalse(hashTable.containsKey(3));
    }

    @Test
    @DisplayName("Проверка удаления элемента из таблицы")
    void removeShouldDeleteElementAndReturnValue() {
        hashTable.put(1, "Value1");
        hashTable.put(2, "Value2");

        assertEquals("Value1", hashTable.remove(1));
        assertEquals(1, hashTable.size());
        assertNull(hashTable.get(1));
        assertEquals("Value2", hashTable.get(2));
    }

    @Test
    @DisplayName("Удаление несуществующего элемента должно возвращать null")
    void removeNonExistingElementShouldReturnNull() {
        hashTable.put(1, "Value1");

        assertNull(hashTable.remove(999));
        assertEquals(1, hashTable.size());
    }

    @Test
    @DisplayName("Проверка метода clear")
    void clearShouldRemoveAllElements() {
        hashTable.put(1, "Value1");
        hashTable.put(2, "Value2");
        hashTable.put(3, "Value3");

        hashTable.clear();

        assertEquals(0, hashTable.size());
        assertTrue(hashTable.isEmpty());
        assertNull(hashTable.get(1));
        assertNull(hashTable.get(2));
        assertNull(hashTable.get(3));
    }

    @Test
    @DisplayName("Проверка обработки коллизий")
    void handleCollisions() {
        // Создаем таблицу с емкостью 5 для большей вероятности коллизий
        HashTable smallTable = new HashTable(5);

        // Добавляем элементы, которые, скорее всего, дадут коллизии
        smallTable.put(0, "Value0");
        smallTable.put(5, "Value5");  // 5 % 5 = 0, коллизия с ключом 0
        smallTable.put(10, "Value10"); // 10 % 5 = 0, коллизия с ключами 0 и 5

        assertEquals("Value0", smallTable.get(0));
        assertEquals("Value5", smallTable.get(5));
        assertEquals("Value10", smallTable.get(10));
        assertEquals(3, smallTable.size());

        // Проверка удаления элемента из середины цепочки
        assertEquals("Value5", smallTable.remove(5));
        assertNull(smallTable.get(5));
        assertEquals("Value0", smallTable.get(0));
        assertEquals("Value10", smallTable.get(10));
        assertEquals(2, smallTable.size());
    }

    @Test
    @DisplayName("Проверка корректной работы с отрицательными ключами")
    void handleNegativeKeys() {
        hashTable.put(-1, "NegativeValue1");
        hashTable.put(-42, "NegativeValue42");

        assertEquals("NegativeValue1", hashTable.get(-1));
        assertEquals("NegativeValue42", hashTable.get(-42));
        assertEquals(2, hashTable.size());
    }

    @Test
    @DisplayName("Проверка автоматического изменения размера таблицы")
    void resizeShouldPreserveAllElements() {
        // Создаем таблицу с небольшой емкостью
        HashTable smallTable = new HashTable(4);

        // Добавляем элементы, чтобы превысить коэффициент загрузки (0.75 * 4 = 3 элемента)
        for (int i = 0; i < 10; i++) {
            smallTable.put(i, "Value" + i);
        }

        // Проверяем, что все элементы сохранились после resize
        assertEquals(10, smallTable.size());
        for (int i = 0; i < 10; i++) {
            assertEquals("Value" + i, smallTable.get(i));
        }
    }

    @Test
    @DisplayName("Проверка удаления элемента из начала цепочки")
    void removeFirstElementInChain() {
        // Создаем таблицу с емкостью 3 для гарантированных коллизий
        HashTable smallTable = new HashTable(3);

        smallTable.put(0, "Value0");
        smallTable.put(3, "Value3");  // 3 % 3 = 0, коллизия
        smallTable.put(6, "Value6");  // 6 % 3 = 0, коллизия

        // Удаляем первый элемент в цепочке (будет зависеть от реализации: либо 0, либо 6)
        String removed = smallTable.remove(0);
        assertEquals("Value0", removed);

        // Проверяем, что остальные элементы остались
        assertNull(smallTable.get(0));
        assertEquals("Value3", smallTable.get(3));
        assertEquals("Value6", smallTable.get(6));
        assertEquals(2, smallTable.size());
    }

    @Test
    @DisplayName("Проверка удаления элемента из конца цепочки")
    void removeLastElementInChain() {
        // Создаем таблицу с емкостью 3 для гарантированных коллизий
        HashTable smallTable = new HashTable(3);

        smallTable.put(0, "Value0");
        smallTable.put(3, "Value3");  // 3 % 3 = 0, коллизия
        smallTable.put(6, "Value6");  // 6 % 3 = 0, коллизия

        // Предполагаем, что элемент с ключом 6 добавлен последним в цепочку
        String removed = smallTable.remove(6);
        assertEquals("Value6", removed);

        // Проверяем, что остальные элементы остались
        assertEquals("Value0", smallTable.get(0));
        assertEquals("Value3", smallTable.get(3));
        assertNull(smallTable.get(6));
        assertEquals(2, smallTable.size());
    }

    @Test
    @DisplayName("Проверка обработки null-значений")
    void handleNullValues() {
        hashTable.put(1, null);

        assertNull(hashTable.get(1));
        assertEquals(1, hashTable.size());
        assertTrue(hashTable.containsKey(1));

        hashTable.put(1, "NotNull");
        assertEquals("NotNull", hashTable.get(1));
        assertEquals(1, hashTable.size());
    }
}