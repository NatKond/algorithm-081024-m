package org.telran.lecture_14_hash.practiceNew;

public class HashTable {
    // Внутренний класс для элемента цепочки
    private class Entry {
        private int key;        // Ключ
        private String value;   // Значение
        private Entry next;     // Ссылка на следующий элемент в цепочке

        public Entry(int key, String value) {
            // TODO 1: инициализировать поля key и value
            // TODO 2: установить next = null
        }

        public int getKey() {
            // TODO: вернуть ключ
            return 0; // заглушка
        }

        public String getValue() {
            // TODO: вернуть значение
            return null; // заглушка
        }

        public void setValue(String value) {
            // TODO: обновить поле value
        }

        public Entry getNext() {
            // TODO: вернуть ссылку на следующий элемент
            return null; // заглушка
        }

        public void setNext(Entry next) {
            // TODO: обновить ссылку next
        }
    }

    private Entry[] table;          // Массив цепочек
    private int capacity;           // Емкость хеш-таблицы
    private int size;               // Количество элементов в таблице
    private final double LOAD_FACTOR = 0.75;

    public HashTable(int initialCapacity) {
        // Проверить initialCapacity > 0, иначе бросить IllegalArgumentException
        // TODO: инициализировать capacity = initialCapacity
        // TODO: создать массив table заданной емкости
        // TODO: size = 0
    }

    public HashTable() {
        // TODO: вызвать конструктор с capacity=16
    }

    private int getIndex(int key) {
        // TODO: вычислить Math.abs(key) % capacity
        return 0; // заглушка
    }

    public void put(int key, String value) {
        // Шаг A: проверить, нужно ли увеличить размер (size/capacity >= LOAD_FACTOR) и вызвать resize()
        // Шаг B: вычислить индекс через getIndex(key)
        // Шаг C: если table[index] == null → создать новую Entry и увеличить size
        // Шаг D: иначе обойти цепочку:
        //   - если найден ключ → обновить значение и выйти
        // Шаг E: добавить новую Entry в конец цепочки, увеличить size
        throw new UnsupportedOperationException("Method put(int key, String value) is not implemented yet");
    }

    private void resize() {
        // Шаг 1: вычислить новыйCapacity = capacity * 2
        // Шаг 2: создать новый массив Entry[newCapacity]
        // Шаг 3: для каждого элемента старой таблицы:
        //   - обходить цепочку, сохранять next
        //   - пересчитать индекс для нового массива
        //   - вставить элемент в начало новой цепочки
        // Шаг 4: заменить table и capacity
    }

    public String get(int key) {
        // Шаг 1: вычислить индекс
        // Шаг 2: обойти цепочку, пока current != null:
        //   - если current.key == key → вернуть current.value
        // Шаг 3: вернуть null, если не найдено
        throw new UnsupportedOperationException("Method get(int key) is not implemented yet");
    }

    public String remove(int key) {
        // Шаг 1: вычислить индекс
        // Шаг 2: если table[index] == null → вернуть null
        // Шаг 3: если первый элемент — искомый ключ:
        //   - сохранить значение, перенаправить head на next, уменьшить size, вернуть значение
        // Шаг 4: обходить цепочку, искать элемент перед искомым:
        //   - при нахождении: убрать элемент из цепочки, уменьшить size, вернуть значение
        // Шаг 5: вернуть null, если не найдено
        throw new UnsupportedOperationException("Method remove(int key) is not implemented yet");
    }

    public boolean containsKey(int key) {
        // TODO: использовать get(key) != null
        throw new UnsupportedOperationException("Method containsKey(int key) is not implemented yet");
    }

    public int size() {
        // TODO: вернуть size
        throw new UnsupportedOperationException("Method size() is not implemented yet");
    }

    public boolean isEmpty() {
        // TODO: вернуть size == 0
        throw new UnsupportedOperationException("Method isEmpty() is not implemented yet");
    }

    public void clear() {
        // TODO: для каждого i от 0 до capacity-1: table[i] = null
        // TODO: size = 0
        throw new UnsupportedOperationException("Method clear() is not implemented yet");
    }

    /**
     * Визуализирует содержимое хеш-таблицы.
     */
    public void printTable() {
        System.out.println("Хеш-таблица (элементов: " + size + ", емкость: " + capacity +
                ", коэффициент загрузки: " + String.format("%.2f", (double) size / capacity) + "):");

        for (int i = 0; i < capacity; i++) {
            System.out.print(i + ": ");

            // Показываем количество элементов в цепочке для наглядности
            int chainLength = 0;
            Entry current = table[i];

            while (current != null) {
                System.out.print("[" + current.getKey() + ":" + current.getValue() + "] → ");
                current = current.getNext();
                chainLength++;
            }

            System.out.println("null" + (chainLength > 0 ? " (длина цепочки: " + chainLength + ")" : ""));
        }
    }

    public static void main(String[] args) {
        // Демонстрация работы хеш-таблицы

        // 1) Создание таблицы
        HashTable table = new HashTable(5);
        System.out.println("Создана пустая хеш-таблица с емкостью 5:");
        table.printTable();
        System.out.println();

        // 2) Добавление элементов с коллизиями
        System.out.println("Добавляем элементы (ожидаются коллизии при индексе 0):");
        table.put(0, "Zero");
        table.put(5, "Five");  // 5 % 5 = 0, коллизия с ключом 0
        table.put(10, "Ten");  // 10 % 5 = 0, коллизия с ключами 0 и 5
        table.printTable();
        System.out.println();

        // Добавляем еще элементы
        table.put(1, "One");
        table.put(2, "Two");
        table.put(3, "Three");
        table.put(4, "Four");
        System.out.println("Добавлены дополнительные элементы:");
        table.printTable();
        System.out.println();

        // 3) Поиск, обновление, удаление
        System.out.println("Поиск элемента с ключом 5: " + table.get(5));

        System.out.println("Обновляем значение для ключа 5:");
        table.put(5, "New Five");
        table.printTable();
        System.out.println();

        System.out.println("Удаляем элемент с ключом 10:");
        table.remove(10);
        table.printTable();
        System.out.println();

        // 4) Проверка resize
        System.out.println("Добавляем еще элементы для проверки resize:");
        table.put(11, "Eleven");
        table.put(12, "Twelve");
        table.put(13, "Thirteen");
        table.put(14, "Fourteen");
        table.printTable();
    }
}
