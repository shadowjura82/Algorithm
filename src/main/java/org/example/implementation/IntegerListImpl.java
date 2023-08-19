package org.example.implementation;

import org.example.exceptons.ItemNotFoundException;
import org.example.exceptons.MyIndexOutOfBoundException;
import org.example.exceptons.MyNullPointerException;
import org.example.interfaces.IntegerList;

import java.util.Arrays;

import static java.lang.System.arraycopy;

public class IntegerListImpl implements IntegerList {
    Integer[] storage;

    public IntegerListImpl(Integer[] storage) {
        this.storage = storage;
    }

    public IntegerListImpl() {
        this.storage = new Integer[0];
    }

    public IntegerListImpl(int size) {
        this.storage = new Integer[size];
        Arrays.fill(storage, 0); // Согласно требованиям не должно быть элементов null
    }

    @Override
    public Integer add(Integer item) {
        isNotNullValidation(item);
        extendArray();
        storage[storage.length - 1] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        boundsValidation(index);
        isNotNullValidation(item);
        extendArray();
        arraycopy(storage, index, storage, index + 1, storage.length - index - 1);
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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals(item)) {
                arraycopy(storage, i + 1, storage, i, storage.length - i - 1);
                i--;
                reduceArray();
            }
        }
        return item;
    }

    @Override
    public Integer remove(int index) {
        boundsValidation(index);
        Integer entry = storage[index];
        arraycopy(storage, index + 1, storage, index, storage.length - index - 1);
        reduceArray();
        return entry;
    }

    @Override
    public boolean contains(Integer item) {
        isNotNullValidation(item);
        Integer[] buff = storage;
        sort(buff);
        if (Arrays.binarySearch(buff, item) > -1) return true;
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        isNotNullValidation(item);
        for (int i = 0; i < storage.length; i++)
            if (storage[i].equals(item))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        isNotNullValidation(item);
        for (int i = storage.length - 1; i >= 0; i--)
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
        return storage.length;
    }

    @Override
    public boolean isEmpty() {
        return storage.length == 0;
    }

    @Override
    public void clear() {
        storage = new Integer[0];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, storage.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }

    private void boundsValidation(int index) {
        if (index < 0 || index >= this.storage.length) throw new MyIndexOutOfBoundException("Нарушены границы массива");
    }

    private void isNotNullValidation(Integer item) {
        if (item == null) throw new MyNullPointerException("Параметр не должен быть null");
    }

    private void containsItemValidation(Integer item) {
        if (!contains(item)) throw new ItemNotFoundException("Элемент не найден в массиве");
    }

    private void reduceArray() {
        Integer[] buff = new Integer[this.storage.length - 1];
        arraycopy(this.storage, 0, buff, 0, buff.length);
        this.storage = buff;
    }

    private void extendArray() {
        Integer[] buff = new Integer[this.storage.length + 1];
        arraycopy(this.storage, 0, buff, 0, this.storage.length);
        this.storage = buff;
    }

    private void sort(Integer[] mas) {
        for (int i = 0; i < mas.length; i++) {
            int minIndex = i;
            for (int j = i; j < mas.length; j++)
                if (mas[j] < mas[minIndex])
                    minIndex = j;
            swapElements(mas, i, minIndex);
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}