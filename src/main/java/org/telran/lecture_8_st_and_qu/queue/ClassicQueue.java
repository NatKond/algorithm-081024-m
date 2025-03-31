package org.telran.lecture_8_st_and_qu.queue;


public class ClassicQueue {
    private int[] items;
    private int head; // Индекс первого элемента
    private int tail; // Индекс последнего элемента
    private int size; // Количество элементов в очереди

    public ClassicQueue(int capacity) {
        items = new int[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void enqueue(int item) {
        if (size == items.length) {
            throw new RuntimeException("Queue is full. Capacity = " + items.length);
        } else {
            items[tail] = item;
            tail = (tail + 1) % items.length; // Циклический буфер
            size++;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty." );
        } else {
            int item = items[head];
            head = (head + 1) % items.length; // Циклический буфер
            size--;
            return item;
        }

    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty." );
        } else {
            return items[head];
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}

