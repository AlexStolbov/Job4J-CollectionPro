package ru.astolbov;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class HashMapLessonTest {

    @Test
    public void whenAddElementThenCollectionHasIt() {
        HashMapLesson<Integer, String> hashMap = new HashMapLesson<>();
        for (int i = 0; i < 100; i++) {
            assertTrue(hashMap.insert(i, Integer.toString(i)));
        }
        for (int i = 0; i < 100; i++) {
            assertTrue(Integer.toString(i).equals(hashMap.get(i)));
        }
    }

    @Test
    public void whenRemoveElementThenCollectionNotHasIt() {
        Integer el1 = 1;
        Integer el2 = 2;
        HashMapLesson<Integer, String> hash = new HashMapLesson<>();
        hash.insert(el1, el1.toString());
        hash.insert(el2, el2.toString());
        hash.delete(el1);
        assertFalse(hash.get(el1) != null);
    }

    @Test
    public void whenAddElementTwiceThenAddIsFalse() {
        HashMapLesson<Integer, String> hash = new HashMapLesson<>();
        Integer one = new Integer(1);
        assertTrue(hash.insert(one, one.toString()));
        assertFalse(hash.insert(one, one.toString()));
    }

    @Test
    public void iteratorTest() {
        HashMapLesson<Integer, String> hml = new HashMapLesson<>();
        hml.insert(1, "one");
        hml.insert(2, "two");
        hml.insert(3, "three");
        for (String el : hml) {
            assertNotNull(el);
        }
        Iterator it = hml.iterator();
        it.next();
        it.remove();
        for (String el : hml) {
            assertNotNull(el);
        }
    }

}