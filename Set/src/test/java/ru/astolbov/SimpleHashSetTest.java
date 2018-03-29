package ru.astolbov;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleHashSetTest {

    @Test
    public void whenAddElementThenCollectionHasIt() {
        SimpleHashSet<Integer> hash = new SimpleHashSet<>();
        for (int i = 0; i < 100; i++) {
            assertTrue(hash.add(i));
        }
        for (int i = 0; i < 100; i++) {
            assertTrue(hash.contains(i));
        }
    }

    @Test
    public void whenRemoveElementThenCollectionNotHasIt() {
        Integer el1 = 1;
        Integer el2 = 2;
        SimpleHashSet<Integer> hash = new SimpleHashSet<>();
        hash.add(el1);
        hash.add(el2);
        hash.remove(el1);
        assertFalse(hash.contains(el1));
    }

    @Test
    public void whenAddElementTwiceThenAddIsFalse() {
        SimpleHashSet<Integer> hash = new SimpleHashSet<>();
        Integer one = new Integer(1);
        assertTrue(hash.add(one));
        assertFalse(hash.add(one));
    }

}