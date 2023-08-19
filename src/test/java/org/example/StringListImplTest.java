package org.example;

import org.example.exceptons.MyIndexOutOfBoundException;
import org.example.exceptons.MyNullPointerException;
import org.example.implementation.StringListImpl;
import org.example.interfaces.StringList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    StringList stringList = new StringListImpl(new String[]{"1", "2", "3", "4", "5"});

    @Test
    @DisplayName("Добавляет новый элемент в конец массива add(String item)")
    void add() {
        final String[] EXPECTED_ARRAY = new String[]{"1", "2", "3", "4", "5", "6"};
        assertEquals("6", stringList.add("6"));
        assertEquals(Arrays.toString(EXPECTED_ARRAY), stringList.toString());
        assertThrows(MyNullPointerException.class, () -> stringList.add(null));
    }

    @Test
    @DisplayName("Добавляет новый элемент в заданную позицию add(int index, String item)")
    void addWithIndex() {
        final String[] EXPECTED_ARRAY = new String[]{"1", "2", "6", "3", "4", "5"};
        assertEquals("6", stringList.add(2, "6"));
        assertEquals(Arrays.toString(EXPECTED_ARRAY), stringList.toString());
        assertThrows(MyNullPointerException.class, () -> stringList.add(null));
        assertThrows(MyIndexOutOfBoundException.class, () -> stringList.add(-1, "test"));
        assertThrows(MyIndexOutOfBoundException.class, () -> stringList.add(6, "test"));
    }

    @Test
    @DisplayName("Изменяет элемент в заданной позиции set(int index, String item)")
    void set() {
        final String[] EXPECTED_ARRAY = new String[]{"1", "2", "33", "4", "5"};
        assertEquals("33", stringList.set(2, "33"));
        assertEquals(Arrays.toString(EXPECTED_ARRAY), stringList.toString());
        assertThrows(MyNullPointerException.class, () -> stringList.add(null));
        assertThrows(MyIndexOutOfBoundException.class, () -> stringList.set(-1, "test"));
        assertThrows(MyIndexOutOfBoundException.class, () -> stringList.set(5, "test"));
    }

    @Test
    @DisplayName("Удаляет элемент по значению remove(String item)")
    void removeByValue() {
        final String[] EXPECTED_ARRAY = new String[]{"1", "2", "4", "5"};
        assertEquals("3", stringList.remove("3"));
        assertEquals(Arrays.toString(EXPECTED_ARRAY), stringList.toString());
        assertThrows(MyNullPointerException.class, () -> stringList.remove(null));
    }

    @Test
    @DisplayName("Удаляет элемент по заданному индексу remove(int index)")
    void removeByIndex() {
        final String[] EXPECTED_ARRAY = new String[]{"1", "2", "4", "5"};
        assertEquals("3", stringList.remove("3"));
        assertEquals(Arrays.toString(EXPECTED_ARRAY), stringList.toString());
        assertThrows(MyIndexOutOfBoundException.class, () -> stringList.remove(-1));
        assertThrows(MyIndexOutOfBoundException.class, () -> stringList.remove(4));
    }

    @Test
    @DisplayName("Проверка на существование элемента contains(String item)")
    void contains() {
        assertTrue(stringList.contains("3"));
        assertFalse(stringList.contains("6"));
        assertThrows(MyNullPointerException.class, () -> stringList.contains(null));
    }

    @Test
    @DisplayName("Возвращает индекс элемента indexOf(String item)")
    void indexOf() {
        final StringList TESTING_LIST = new StringListImpl(new String[]{"1", "2", "3", "22", "3", "4", "5"});
        assertEquals(2, TESTING_LIST.indexOf("3"));
        assertEquals(-1, TESTING_LIST.indexOf("6"));
        assertThrows(MyNullPointerException.class, () -> stringList.contains(null));
    }

    @Test
    @DisplayName("Возвращает индекс элемента с конца массива indexOf(String item)")
    void lastIndexOf() {
        final StringList TESTING_LIST = new StringListImpl(new String[]{"1", "2", "3", "22", "3", "4", "5"});
        assertEquals(4, TESTING_LIST.lastIndexOf("3"));
        assertEquals(-1, TESTING_LIST.lastIndexOf("6"));
        assertThrows(MyNullPointerException.class, () -> stringList.contains(null));
    }

    @Test
    @DisplayName("Получает элемент по индексу get(int index)")
    void get() {
        assertEquals("3", stringList.get(2));
        assertThrows(MyIndexOutOfBoundException.class, () -> stringList.get(-1));
        assertThrows(MyIndexOutOfBoundException.class, () -> stringList.get(5));
    }

    @Test
    @DisplayName("Сравнивает два списка между собой equals(StringList otherList)")
    void testEquals() {
        StringList stringList1 = new StringListImpl(new String[]{"1", "2", "3", "4", "5"});
        assertTrue(stringList.equals(stringList1));
        stringList1.add("test");
        assertFalse(stringList.equals(stringList1));
        assertTrue(stringList.equals(stringList));
        assertThrows(MyNullPointerException.class, () -> stringList.equals(null));
        assertFalse(stringList.equals(new String("tst")));
    }

    @Test
    @DisplayName("Возвращает величину массива int size()")
    void size() {
        assertEquals(5, stringList.size());
    }

    @Test
    @DisplayName("Проверяет, пустой массив или нет int size()")
    void isEmpty() {
        assertFalse(stringList.isEmpty());
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    @DisplayName("Очищает массив void clear()")
    void clear() {
        assertFalse(stringList.isEmpty());
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    @DisplayName("Преобразовывает лист в массив String[] toArray()")
    void toArray() {
        String[] array = new String[]{"1", "2", "3", "4", "5"};
        assertArrayEquals(array, stringList.toArray());
    }

    @Test
    @DisplayName("Преобразовывает лист в строку String[] toString()")
    void testToString() {
        String string = "[1, 2, 3, 4, 5]";
        assertEquals(string, stringList.toString());
    }
}