package org.telran.lecture_14_hash.practiceNew;

import java.util.NoSuchElementException;

public class Library {
    private class Node {
        private int key;
        private String book;
        private Node next;

        public Node(int key, String book) {
            this.key = key;
            this.book = book;
        }

        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", book='" + book + '\'' +
                    ", next=" + next +
                    '}';
        }
    }


    private Node[] books;

    public Library(int maxSize) {
        books = new Node[maxSize];
    }

    public void add(int key, String book) {
        int index = getIndex(key);
        Node node = books[index];
        if (node == null) {
            books[index] = new Node(key, book);
            return;
        }
        Node previousNode = null;
        while (node != null) {
            if (node.key == key) {
                node.book = book;
                return;
            }
            previousNode = node;
            node = node.next;
        }
        previousNode.next = new Node(key, book);
    }

    private int getIndex(int key) {
        int realIndex = key % books.length;
       /* System.out.println("Index for insert to book array = " + realIndex  + " my key = " + key);
        System.out.println("Деление = " + key/books.length);
        System.out.println("Остаток от деления = " + key % books.length);*/
        return realIndex;
    }

    public String getBook(int key) {
        int index = getIndex(key);
        Node node = books[index];

        while (node != null) {
            if (node.key == key) {
                return node.book;
            }
            node = node.next;
        }
        throw new NoSuchElementException("Книги с номером = " + key + " нет в нашей библиотеке");
    }

    public void printTable() {
        for (int i = 0; i < books.length; i++) {
            System.out.print(i + ": ");

            // Показываем количество элементов в цепочке для наглядности
            int chainLength = 0;
            Node current = books[i];

            while (current != null) {
                System.out.print("[" + current.key + ":" + current.book + "] → ");
                current = current.next;
                chainLength++;
            }

            System.out.println("null" + (chainLength > 0 ? " (длина цепочки: " + chainLength + ")" : ""));
        }
    }

    public String remove(int key) {
        int index = getIndex(key);
        if (index < 0) {
            return null;
        }
        Node node = books[index];
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            String book = node.book;
            books[index] = node.next;
            return book;
        }

        while (node.key != key) {
            node = node.next;
            if (node == null) {
                return null;
            }
        }
        return node.book;
    }


    public static void main(String[] args) {
        Library library = new Library(11);

        library.add(2, "Book 1");
        library.add(1, "Book 2");
        library.add(2, "Book 3");
        library.add(9, "Book 4");
        library.add(11, "Book 5");
        library.add(100, "Book 6");
        library.printTable();
        System.out.println("library.remove(11) = " + library.remove(11));
        System.out.println("library.remove(1) = " + library.remove(1));
        //System.out.println(Arrays.toString(library.books));
        library.printTable();
        System.out.println("library.getBook(9) = " + library.getBook(9));
        System.out.println("library.getBook(11) = " + library.getBook(11));



    }
}
