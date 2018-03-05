package ru.astolbov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {

    @Test
    public void whenAddElementThenGrowSize() {
        SimpleHashSet<Integer> hash = new SimpleHashSet<>();
        hash.add(1);
        assertThat(hash.getSize(), is(1));
    }

    @Test
    public void whenAddAndRemoveElementThenSizeNotChange() {
        SimpleHashSet<Integer> hash = new SimpleHashSet<>();
        Integer one = new Integer(1);
        hash.add(one);
        hash.remove(one);
        assertThat(hash.getSize(), is(0));
        hash.add(one);
        hash.remove(2);
        assertThat(hash.getSize(), is(1));
    }

    @Test
    public void whenAddElementTwiceThenSizeNotChange() {
        SimpleHashSet<Integer> hash = new SimpleHashSet<>();
        Integer one = new Integer(1);
        hash.add(one);
        hash.add(one);
        assertThat(hash.getSize(), is(1));
    }

}