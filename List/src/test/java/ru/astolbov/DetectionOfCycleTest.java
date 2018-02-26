package ru.astolbov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DetectionOfCycleTest {

    @Test
    public void whenNoElementsThenReturnFalse() {
        boolean res = new DetectionOfCycle().hasCycle(null);
        assertThat(res, is(false));
    }

    @Test
    public void whenOneElementsThenReturnFalse() {
        DetectionOfCycle.NodeCycle first = new DetectionOfCycle.NodeCycle<>(1);
        boolean res = new DetectionOfCycle().hasCycle(first);
        assertThat(res, is(false));
    }

    @Test
    public void whenTwoElementsAndOneNotNullNextThenReturnFalse() {
        DetectionOfCycle.NodeCycle first = new DetectionOfCycle.NodeCycle<>(1);
        DetectionOfCycle.NodeCycle two = new DetectionOfCycle.NodeCycle<>(2);
        first.next = two;
        boolean res = new DetectionOfCycle().hasCycle(first);
        assertThat(res, is(false));
    }

    @Test
    public void whenNoCycleThenReturnFalse() {
        DetectionOfCycle.NodeCycle first = new DetectionOfCycle.NodeCycle<>(1);
        DetectionOfCycle.NodeCycle two = new DetectionOfCycle.NodeCycle<>(2);
        DetectionOfCycle.NodeCycle third = new DetectionOfCycle.NodeCycle<>(3);
        first.next = two;
        two.next = third;
        boolean res = new DetectionOfCycle().hasCycle(first);
        assertThat(res, is(false));
        res = new DetectionOfCycle().hasCycle(two);
        assertThat(res, is(false));
        res = new DetectionOfCycle().hasCycle(third);
        assertThat(res, is(false));
    }

    @Test
    public void whenCycleThenReturnTrue() {
        DetectionOfCycle.NodeCycle first = new DetectionOfCycle.NodeCycle<>(1);
        DetectionOfCycle.NodeCycle two = new DetectionOfCycle.NodeCycle<>(2);
        DetectionOfCycle.NodeCycle third = new DetectionOfCycle.NodeCycle<>(3);
        DetectionOfCycle.NodeCycle four = new DetectionOfCycle.NodeCycle<>(4);
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        boolean res = new DetectionOfCycle().hasCycle(first);
        assertThat(res, is(true));
    }

    @Test
    public void whenLocalCycleThenReturnTrue() {
        int testSize = 20;
        DetectionOfCycle.NodeCycle[] all = new DetectionOfCycle.NodeCycle[testSize];
        for (int i = 0; i < testSize; i++) {
            all[i] = new DetectionOfCycle.NodeCycle(i);
        }
        for (int i = 0; i < testSize - 1; i++) {
            all[i].next = all[i + 1];
        }
        all[10].next = all[8];
        boolean res = new DetectionOfCycle().hasCycle(all[0]);
        assertThat(res, is(true));
    }

}