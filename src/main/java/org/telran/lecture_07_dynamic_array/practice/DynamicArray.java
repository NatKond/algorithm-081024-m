package org.telran.lecture_07_dynamic_array.practice;

import java.util.Arrays;

public class DynamicArray {
    private int[] array; // внутренний массив для хранения элементов
    private int count; // логический размер - сколько элементов храним
    private int size; // физический размер - сколько памяти выделили

    public DynamicArray() {
        array = new int[1]; // 1 -> 2 -> 4 -> 8 -> 16 -> 32
        count = 0;
        size = 1;
    }

    public DynamicArray(int[] initialArray) {
        array = Arrays.copyOf(initialArray, initialArray.length);
        count = initialArray.length;
        size = initialArray.length;
    }

    private void growSize() {
        int[] tmp = new int[size * 2]; // Создаем новый в 2 раза больше
        // Копируем элементы из старого
        for (int i = 0; i < size; i++) {
            tmp[i] = array[i];
        }
        array = tmp;
        size *= 2;
    }


    // function add an element at the end of array
    public void add(int data) {
        if (count >= size) { // Есть места не хватает
            growSize();
        }
        array[count] = data;
        count++;
    }

    // function remove last element
    public void remove() {
        if (this.count == 0) {
            throw new Error("The array is empty.");
        }
        this.count--;
    }

    // function add an element at given index
    public void addAt(int index, int data) {
        if (count >= size) { // Есть места хватает
            growSize();
        }
        for (int i = count - 1; i >= index; i--) {
            array[i + 1] = array[i]; // сдвигаем все элементы вправо от текущего индекса
        }

        array[index] = data;
        count++;
    }

    // function shift all element of right side from given index in left
    public void removeAt(int index) {
        if (count > 0) {
            for (int i = index; i < count - 1; i++) {
                array[i] = array[i + 1]; // сдвигаем все элементы влево до текущего индекса
            }
            array[count - 1] = 0;
            count--;
        }
    }

    public int[] getArray() {
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public void set(int index, int data) {
        if (index >= count) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + count);
        }
        array[index] = data;
    }

    @Override
    public String toString() {
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = array[i];
        }
        return Arrays.toString(result);
    }

    public void clean() {
        array = new int[1];
        count = 0;
        size = 1;
    }

    public int length() {
        return count;
    }

    public static void main(String[] args) {
        DynamicArray data = new DynamicArray(new int[]{2, 4, 6, 8});
        data.add(1);
        data.add(2);
        data.add(3);

        System.out.println(data + ", size =  " + data.length());
        data.set(6, 4);
        System.out.println(data + ", size =  " + data.length());
        data.clean();
        System.out.println(data + ", size =  " + data.length());
    }
}
