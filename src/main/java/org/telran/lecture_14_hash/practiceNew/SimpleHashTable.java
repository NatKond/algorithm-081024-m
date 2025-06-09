package org.telran.lecture_14_hash.practiceNew;

public class SimpleHashTable {
    static class Node {
        int key;
        String value;
        Node next;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] table;

    public SimpleHashTable(int capacity) {
        table = new Node[capacity];
    }

    private int hash(int key) {
        return Math.abs(key) % table.length;
    }

    public void put(int key, String value) {
        int index = hash(key);
        Node current = table[index];

        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = table[index];
        table[index] = newNode;
    }

    public String get(int key) {
        int index = hash(key);
        Node current = table[index];

        while (current != null) {
            if (current.key == key) return current.value;
            current = current.next;
        }

        return null;
    }

    public void remove(int key) {
        int index = hash(key);
        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + ": ");
            Node current = table[i];
            while (current != null) {
                System.out.print("[" + current.key + ":" + current.value + "] -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        SimpleHashTable table = new SimpleHashTable(10);

        table.put(1, "One");
        table.put(5, "Five");
        table.put(9, "Nine");
        table.put(2, "Two");

        table.printTable();

        System.out.println("Get 5: " + table.get(5));

        table.remove(5);
        table.printTable();
    }
}
