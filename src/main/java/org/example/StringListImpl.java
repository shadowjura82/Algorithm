package org.example;

import java.util.Arrays;

import static java.lang.System.arraycopy;

public class StringListImpl implements StringList {
    String[] string;

    public StringListImpl(int arraySize) {
        this.string = new String[arraySize];
        Arrays.fill(this.string, "");        // По требованию в массиве не должно быть null
    }

    public StringListImpl(String[] string) {
        this.string = string;
    }

    @Override
    public String add(String item) {
        isNotNullValidation(item);
        extendArray();
        this.string[this.string.length - 1] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        boundsValidation(index);
        isNotNullValidation(item);
        extendArray();
        for (int i = this.string.length - 1; i > index; i--)
            this.string[i] = this.string[i - 1];
        this.string[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        boundsValidation(index);
        isNotNullValidation(item);
        this.string[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        isNotNullValidation(item);
        containsItemValidation(item);
        int count = 0;
        for (int i = 0; i < this.string.length; i++) {
            if (this.string[i].equals(item)) {
                moveElements(i);
                count++;
            }
        }
        for (int i = 0; i < count; i++) reduceArray();
        return item;
    }

    @Override
    public String remove(int index) {
        boundsValidation(index);
        String value = this.string[index];
        moveElements(index);
        reduceArray();
        return value;
    }

    @Override
    public boolean contains(String item) {
        isNotNullValidation(item);
        for (String entry : this.string)
            if (entry.equals(item)) return true;
        return false;
    }

    @Override
    public int indexOf(String item) {
        isNotNullValidation(item);
        if (!contains(item)) {
            return -1;
        } else {
            for (int i = 0; i < this.string.length; i++)
                if (this.string[i].equals(item))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        isNotNullValidation(item);
        if (!contains(item)) {
            return -1;
        } else {
            for (int i = this.string.length - 1; i >= 0; i--)
                if (this.string[i].equals(item))
                    return i;
        }
        return -1;
    }

    @Override
    public String get(int index) {
        boundsValidation(index);
        return this.string[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (this == otherList) return true;
        if (otherList == null) throw new MyNullPointerException("Передан параметр null");
        if (getClass() != otherList.getClass()) return false;
        StringListImpl that = (StringListImpl) otherList;
        return Arrays.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(string);
    }

    @Override
    public int size() {
        return this.string.length;
    }

    @Override
    public boolean isEmpty() {
        return this.string.length == 0;
    }

    @Override
    public void clear() {
        this.string = new String[0];
    }

    @Override
    public String[] toArray() {
        return this.string;
    }

    public String toString() {
        return Arrays.toString(this.string);
    }

    private void boundsValidation(int index) {
        if (index < 0 || index >= this.string.length) throw new MyIndexOutOfBoundException("Нарушены границы массива");
    }

    private void isNotNullValidation(String item) {
        if (item == null) throw new MyNullPointerException("Параметр не должен быть null");
    }

    private void containsItemValidation(String item) {
        if (!contains(item)) throw new ItemNotFoundException("Элемент не найден в массиве");
    }

    private void extendArray() {
        String[] buff = new String[this.string.length + 1];
        arraycopy(this.string, 0, buff, 0, this.string.length);
        this.string = buff;
    }

    private void reduceArray() {
        String[] buff = new String[this.string.length - 1];
        arraycopy(this.string, 0, buff, 0, buff.length);
        this.string = buff;
    }

    private void moveElements(int index) {
        for (int i = index; i < this.string.length - 1; i++)
            this.string[i] = this.string[i + 1];
    }
}