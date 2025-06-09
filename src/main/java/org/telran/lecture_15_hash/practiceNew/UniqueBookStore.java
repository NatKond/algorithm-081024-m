package org.telran.lecture_15_hash.practiceNew;

import java.util.Arrays;

/**
 * Хеш-набор книг с уникальными значениями, реализованный на основе CommonHashTable.
 * Не допускает дубликатов и обеспечивает быстрый поиск, добавление и удаление книг.
 */
public class UniqueBookStore {

    // 1. Объяви поле books — для хранения уникальных книг.
    private CommonHashTable books;

    /**
     * Конструктор, инициализирующий таблицу с заданной вместимостью.
     *
     * @param initialCapacity начальная ёмкость хеш-таблицы
     */
    public UniqueBookStore(int initialCapacity) {
        // 2. Создай новый объект CommonHashTable с переданной вместимостью.
        books = new CommonHashTable(initialCapacity);
    }

    public UniqueBookStore() {
        // 2. Создай новый объект CommonHashTable с переданной вместимостью.
        books = new CommonHashTable();
    }

    /**
     * Возвращает текущее количество уникальных книг.
     *
     * @return количество элементов
     */
    public int size() {
        return books.size();
    }

    /**
     * Добавляет книгу, если она ещё не существует.
     *
     * @param book название книги
     * @return true, если книга добавлена, иначе false
     */
    public boolean add(String book) {
        // 4. Если books уже содержит такой ключ — верни false.
        if (books.containsKey(book)) return false;
        // 5. Иначе добавь книгу в таблицу с ключом и значением book.
        books.put(book, book);
        return true;
    }

    /**
     * Проверяет, содержится ли книга в наборе.
     *
     * @param book название книги
     * @return true, если книга есть в библиотеке
     */
    public boolean contains(String book) {
        return books.containsKey(book);
    }

    /**
     * Удаляет книгу по названию.
     *
     * @param book название книги
     * @return название удалённой книги или null
     */
    public String remove(String book) {
        // 7. Удали запись по ключу book и приведи результат к String.
        return (String) books.remove(book);
    }

    /**
     * Печатает содержимое таблицы и общее количество книг.
     */
    public void printTable() {
        System.out.println("-".repeat(5) + " UniqueBookStore " + "-".repeat(5));
        books.printTable();
        // 8. Выведи заголовок, вызови printTable() у books и выведи итоговое количество книг.
    }

    /**
     * Печатает список всех книг.
     */
    public void printAllBooks() {
        // 9. Выведи заголовок, затем пройдись по массиву values() и выведи каждую строку.
        System.out.println("-".repeat(5) + " All Books in UniqueBookStore " + "-".repeat(5));
        System.out.println(Arrays.toString(books.keys()));
    }

    /**
     * Демонстрация работы UniqueBookStore.
     */
    public static void main(String[] args) {
        // 10. Создай экземпляр UniqueBookStore с вместимостью 8.
        UniqueBookStore library = new UniqueBookStore(8);

        System.out.println("=== Демонстрация HashSet для библиотеки ===");
        System.out.println("Добавляем книги в библиотеку:");

        // 11. Объяви массив строк с названиями книг.
        String[] booksToAdd = {
                "Война и мир",
                "Преступление и наказание",
                "Мастер и Маргарита",
                "1984",
                "Гарри Поттер",
                "Властелин колец",
        };

        // 12. Пройди циклом по массиву, вызывая add() и печатая результат.
        for (String book : booksToAdd) {
            boolean added = library.add(book);
            System.out.println("Добавляем '" + book + "': " + (added ? "успешно" : "уже существует"));
        }

        // 13. Выведи текущее состояние таблицы.
        library.printTable();
        System.out.println("library.contains(\"1984\") = " + library.contains("1984"));
        // 14. Попробуй добавить дубликат — "1984".
        library.add("1984");

        library.printTable();
        library.printAllBooks();
    }
}
