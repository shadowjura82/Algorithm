package org.example;

import org.example.exceptons.MyIndexOutOfBoundException;
import org.example.exceptons.MyNullPointerException;
import org.example.implementation.IntegerListImpl;
import org.example.interfaces.IntegerList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerListImplTest {
    IntegerList integerList = new IntegerListImpl(new Integer[]{1, 2, 3, 4, 5});

    @Test
    @DisplayName("Добавляет новый элемент в конец массива add(Integer item)")
    void add() {
        final Integer[] EXPECTED_ARRAY = new Integer[]{1, 2, 3, 4, 5, 6};
        assertEquals(6, integerList.add(6));
        assertArrayEquals(EXPECTED_ARRAY, integerList.toArray());
        assertThrows(MyNullPointerException.class, () -> integerList.add(null));
    }

    @Test
    @DisplayName("Добавляет новый элемент в заданную позицию add(int index, Integer item)")
    void addWithIndex() {
        final Integer[] EXPECTED_ARRAY = new Integer[]{1, 2, 33, 3, 4, 5};
        assertEquals(33, integerList.add(2, 33));
        assertArrayEquals(EXPECTED_ARRAY, integerList.toArray());
        assertThrows(MyNullPointerException.class, () -> integerList.add(2, null));
        assertThrows(MyIndexOutOfBoundException.class, () -> integerList.add(-1, 1));
        assertThrows(MyIndexOutOfBoundException.class, () -> integerList.add(6, 1));
    }

    @Test
    @DisplayName("Изменяет элемент в заданной позиции set(int index, Integer item)")
    void set() {
        final Integer[] EXPECTED_ARRAY = new Integer[]{1, 2, 33, 4, 5};
        assertEquals(33, integerList.set(2, 33));
        assertArrayEquals(EXPECTED_ARRAY, integerList.toArray());
        assertThrows(MyNullPointerException.class, () -> integerList.set(2, null));
        assertThrows(MyIndexOutOfBoundException.class, () -> integerList.set(-1, 1));
        assertThrows(MyIndexOutOfBoundException.class, () -> integerList.set(5, 1));
    }

    @Test
    @DisplayName("Удаляет элемент по значению remove(Integer item)")
    void removeByValue() {
        final Integer[] EXPECTED_ARRAY = new Integer[]{1, 2, 4, 5};
        assertEquals(3, integerList.remove((Integer) 3));
        assertArrayEquals(EXPECTED_ARRAY, integerList.toArray());
        assertThrows(MyNullPointerException.class, () -> integerList.remove(null));
    }

    @Test
    @DisplayName("Удаляет элемент по заданному индексу remove(int index)")
    void removeByIndex() {
        final Integer[] EXPECTED_ARRAY = new Integer[]{1, 2, 4, 5};
        assertEquals(3, integerList.remove(2));
        assertArrayEquals(EXPECTED_ARRAY, integerList.toArray());
        assertThrows(MyIndexOutOfBoundException.class, () -> integerList.remove(-1));
        assertThrows(MyIndexOutOfBoundException.class, () -> integerList.remove(4));
    }

    @Test
    @DisplayName("Проверка на существование элемента contains(Integer item)")
    void contains() {
        assertTrue(integerList.contains(3));
        assertFalse(integerList.contains(6));
        assertThrows(MyNullPointerException.class, () -> integerList.contains(null));
    }

    @Test
    @DisplayName("Возвращает индекс элемента indexOf(Integer item)")
    void indexOf() {
        final IntegerList TESTING_LIST = new IntegerListImpl(new Integer[]{1, 2, 3, 22, 3, 4, 5});
        assertEquals(2, TESTING_LIST.indexOf(3));
        assertEquals(-1, TESTING_LIST.indexOf(6));
        assertThrows(MyNullPointerException.class, () -> integerList.contains(null));
    }

    @Test
    @DisplayName("Возвращает индекс элемента с конца массива indexOf(Integer item)")
    void lastIndexOf() {
        final IntegerList TESTING_LIST = new IntegerListImpl(new Integer[]{1, 2, 3, 22, 3, 4, 5});
        assertEquals(4, TESTING_LIST.lastIndexOf(3));
        assertEquals(-1, TESTING_LIST.lastIndexOf(6));
        assertThrows(MyNullPointerException.class, () -> integerList.contains(null));
    }

    @Test
    @DisplayName("Получает элемент по индексу get(int index)")
    void get() {
        assertEquals(3, integerList.get(2));
        assertThrows(MyIndexOutOfBoundException.class, () -> integerList.get(-1));
        assertThrows(MyIndexOutOfBoundException.class, () -> integerList.get(5));
    }

    @Test
    @DisplayName("Сравнивает два списка между собой equals(IntegerList otherList)")
    void testEquals() {
        IntegerList integerList1 = new IntegerListImpl(new Integer[]{1, 2, 3, 4, 5});
        assertTrue(integerList.equals(integerList1));
        integerList1.add(2);
        assertFalse(integerList.equals(integerList1));
        assertTrue(integerList.equals(integerList));
        assertThrows(MyNullPointerException.class, () -> integerList.equals(null));
        assertFalse(integerList.equals(new String("tst")));
    }

    @Test
    @DisplayName("Возвращает величину массива int size()")
    void size() {
        assertEquals(5, integerList.size());
    }

    @Test
    @DisplayName("Проверяет, пустой массив или нет int size()")
    void isEmpty() {
        assertFalse(integerList.isEmpty());
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    @DisplayName("Очищает массив void clear()")
    void clear() {
        assertFalse(integerList.isEmpty());
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    @DisplayName("Преобразовывает лист в массив Integer[] toArray()")
    void toArray() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        assertArrayEquals(array, integerList.toArray());
    }

    @Test
    @DisplayName("Преобразовывает лист в строку String[] toString()")
    void testToString() {
        String string = "[1, 2, 3, 4, 5]";
        assertEquals(string, integerList.toString());
    }
}