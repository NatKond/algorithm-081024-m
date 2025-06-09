package org.telran.lecture_15_hash.practiceNew;

import java.util.Arrays;

/**
 * Простая реализация хеш-таблицы с цепочечной обработкой коллизий.
 * Использует массив связных списков (Entry), коэффициент загрузки и простую хеш-функцию.
 */
public class CommonHashTable {

    /**
     * Внутренний класс, представляющий элемент таблицы.
     */
    private class Entry {
        private Object key;
        private Object value;
        private Entry next;

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }

        /**
         * Конструктор создаёт новую запись с заданным ключом и значением.
         *
         * @param key   ключ
         * @param value значение
         */
        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }
    }

    private Entry[] table;
    private int capacity;
    private int size;
    // 7. Установи константу LOAD_FACTOR (например, 0.75).

    /**
     * Конструктор с указанием начальной ёмкости.
     *
     * @param initialCapacity начальная вместимость таблицы
     */
    public CommonHashTable(int initialCapacity) {
        // 8. Проверь, что initialCapacity > 0, иначе выбрось IllegalArgumentException.
        // 9. Инициализируй table с указанной вместимостью.
        // 10. Установи начальное значение size = 0.
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }
        this.capacity = initialCapacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    /**
     * Конструктор по умолчанию (вместимость 16).
     */
    public CommonHashTable() {
        // 11. Вызови основной конструктор с параметром 16.
        this(16);
    }

    /**
     * Хеш-функция для ключей.
     *
     * @param key ключ
     * @return положительное хеш-значение
     */
    private int hash(Object key) {
        if (key instanceof Number) {
            return Math.abs((int) key);
        } else if (key instanceof String) {
            String input = (String) key;
            if (input == null) {
                return 0;
            }
            int hash = 0;
            int magicNumber = 31;
            for (int i = 0; i < input.length(); i++) {
                hash = hash * magicNumber + input.charAt(i);
            }
            return Math.abs(hash);
        } else {
            throw new UnsupportedOperationException("This method doesn't supported for this data type");
        }
    }

    /**
     * Получает индекс массива по ключу.
     *
     * @param key ключ
     * @return индекс в массиве table
     */
    private int getIndex(Object key) {
        // 16. Верни hash(key) % capacity.
        int hash = hash(key);
        int index = hash % capacity;
        //System.out.println("for key = " + key + " hash = " + hash + " index = " + index + " length of array = " + table.length);
        return index;
    }

    /**
     * Добавляет или обновляет элемент по ключу.
     *
     * @param key   ключ
     * @param value значение
     */
    public void put(Object key, Object value) {
        // 17. Если текущая загрузка (size / capacity) >= LOAD_FACTOR — вызови resize().
        // 18. Получи индекс для key через getIndex(key).
        if ((double) size / capacity >= 0.75) {
            resize();
        }

        int index = getIndex(key);

        Entry newEntry = new Entry(key, value);
        // 19. Создай новый Entry(key, value).
        // 20. Если table[index] == null — запиши новый элемент и увеличь size.
        if (table[index] == null) {
            table[index] = newEntry;
            size++;
        } else {
            Entry current = table[index];
            if (current.key.equals(key)) {
                current.value = value;
            }
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newEntry;
            size++;
        }
        // 21. Иначе — пройдись по цепочке:
        //     а. Если найден элемент с таким же key — обнови его value и вернись.
        //     б. Иначе — добавь новый элемент в конец цепочки и увеличь size.
    }

    /**
     * Увеличивает размер таблицы и перераспределяет все элементы.
     */
    private void resize() {
        int oldCapacity = capacity;
        Entry[] oldEntries = table;
        capacity = (int) (capacity * 1.5);
        table = new Entry[capacity];
        size = 0;
        for (int i = 0; i < oldCapacity; i++) {
            Entry entry = oldEntries[i];
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    /**
     * Получает значение по ключу.
     *
     * @param key ключ
     * @return значение или null
     */
    public Object get(Object key) {
        int index = getIndex(key);
        Entry entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    /**
     * Удаляет элемент по ключу.
     *
     * @param key ключ
     * @return значение удалённого элемента или null
     */
    public Object remove(Object key) {
        int index = getIndex(key);

        Entry entry = table[index];
        if (entry == null) {
            return null;
        }
        if (entry.key.equals(key)) {
            Object value = entry.value;
            table[index] = entry.next;
            size--;
            return value;
        }
        while (entry.next != null) {
            if (entry.next.key.equals(key)) {
                Object value = entry.next.value;
                entry.next = entry.next.next;
                size--;
                return value;
            }
        }
        return null;
    }

    /**
     * Проверяет наличие ключа.
     *
     * @param key ключ
     * @return true, если ключ найден
     */
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * Возвращает количество элементов.
     *
     * @return размер таблицы
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуста ли таблица.
     *
     * @return true, если size == 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Очищает таблицу.
     */
    public void clear() {
        table = new Entry[capacity];
        size = 0;
    }

    /**
     * Возвращает массив всех значений.
     *
     * @return массив значений
     */
    public Object[] values() {
        // 35. Создай массив размера size.
        Object[] result = new Object[size];
        // 36. Пройдись по всем цепочкам в table и скопируй значения в массив.
        int index = 0;
        for (Entry entry : table) {
            Entry current = entry;
            while (current != null) {
                result[index] = current.getValue();
                index++;
                current = current.getNext();
            }

        }
        return result;
    }

    public Object[] keys() {
        // 35. Создай массив размера size.
        Object[] result = new Object[size];
        // 36. Пройдись по всем цепочкам в table и скопируй ключи в массив.
        int index = 0;
        for (Entry entry : table) {
            Entry current = entry;
            while (current != null) {
                result[index] = current.getKey();
                index++;
                current = current.getNext();
            }
        }
        return result;
    }

    /**
     * Выводит таблицу в консоль (для отладки).
     */
    public void printTable() {

        System.out.println("-".repeat(5) + "CommonHashTable" + "-".repeat(5));
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + ": ");

            // Показываем количество элементов в цепочке для наглядности
            int chainLength = 0;
            Entry current = table[i];

            while (current != null) {
                System.out.print("[" + current.key + ":" + current.value + "] → ");
                current = current.next;
                chainLength++;
            }

            System.out.println("null" + (chainLength > 0 ? " (длина цепочки: " + chainLength + ")" : ""));
        }
    }

    /**
     * Пример использования.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {

        CommonHashTable hashTable = new CommonHashTable(4);

        hashTable.put("1234", "Book1");
        hashTable.put("ased4", "Book2");
        hashTable.put("4", "Book22");
        hashTable.printTable();
        hashTable.put("s3r0", "Book3");
        hashTable.put("3224", "Book4");
        hashTable.printTable();
        hashTable.put("32ws1111111111", "Book5");
        hashTable.printTable();

        System.out.println("hashTable.containsKey(\"1234\") = " + hashTable.containsKey("1234"));
        System.out.println("hashTable.containsKey(\"2234\") = " + hashTable.containsKey("2234"));
        System.out.println("hashTable.containsKey(\"32ws1111111111\") = " + hashTable.containsKey("32ws1111111111"));
        System.out.println(hashTable.get("3224"));

        System.out.println(Arrays.toString(hashTable.values()));
        System.out.println(Arrays.toString(hashTable.keys()));

        // 39. Создай таблицу и вызови put() с разными ключами.
        // 40. Вызови printTable(), get(), remove(), containsKey() для проверки.
    }
}
