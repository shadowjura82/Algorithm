package org.example.implementation;

import org.example.exceptons.ItemNotFoundException;
import org.example.exceptons.MyIndexOutOfBoundException;
import org.example.exceptons.MyNullPointerException;
import org.example.interfaces.IntegerList;

import java.util.Arrays;

import static java.lang.System.arraycopy;

public class IntegerListImpl implements IntegerList {
    Integer[] storage;
    int size = 0;

    public IntegerListImpl(Integer[] storage) {
        this.storage = storage;
        size = storage.length;
    }

    public IntegerListImpl() {
        this.storage = new Integer[0];
    }

    public IntegerListImpl(int size) {
        this.storage = new Integer[size];
        Arrays.fill(storage, 0); // Согласно требованиям не должно быть элементов null
        this.size = size;
    }

    @Override
    public Integer add(Integer item) {
        isNotNullValidation(item);
        if (this.storage.length == size) grow();
        storage[size - 1] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        boundsValidation(index);
        isNotNullValidation(item);
        if (this.storage.length == size) grow();
        arraycopy(storage, index, storage, index + 1, size - index - 1);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        isNotNullValidation(item);
        boundsValidation(index);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        isNotNullValidation(item);
        containsItemValidation(item);
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                arraycopy(storage, i + 1, storage, i, size - i - 1);
                i--;
                size--;
            }
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        boundsValidation(index);
        Integer entry = storage[index];
        arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
        return entry;
    }

    @Override
    public boolean contains(Integer item) {
        isNotNullValidation(item);
        Integer[] buff = storage;
        sort(buff, 0, buff.length - 1);
        if (Arrays.binarySearch(buff, item) > -1) return true;
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        isNotNullValidation(item);
        for (int i = 0; i < size; i++)
            if (storage[i].equals(item))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        isNotNullValidation(item);
        for (int i = size - 1; i >= 0; i--)
            if (storage[i].equals(item))
                return i;
        return -1;
    }

    @Override
    public Integer get(int index) {
        boundsValidation(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (this == otherList) return true;
        if (otherList == null) throw new MyNullPointerException("Передан параметр null");
        if (getClass() != otherList.getClass()) return false;
        IntegerListImpl that = (IntegerListImpl) otherList;
        return Arrays.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(storage);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }

    private void boundsValidation(int index) {
        if (index < 0 || index >= size) throw new MyIndexOutOfBoundException("Нарушены границы массива");
    }

    private void isNotNullValidation(Integer item) {
        if (item == null) throw new MyNullPointerException("Параметр не должен быть null");
    }

    private void containsItemValidation(Integer item) {
        if (!contains(item)) throw new ItemNotFoundException("Элемент не найден в массиве");
    }

    private void grow() {
        Integer[] buff = new Integer[this.storage.length + (this.storage.length / 2)];
        arraycopy(this.storage, 0, buff, 0, size);
        this.size++;
        this.storage = buff;
    }

    private void sort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            sort(arr, begin, partitionIndex - 1);
            sort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}