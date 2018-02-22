package ru.astolbov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DetectionOfCycleTest {

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
        boolean res = DetectionOfCycle.hasCycle(first);
        assertThat(res, is(true));
    }

    @Test
    public void whenNoCycleThenReturnFalse() {
        DetectionOfCycle.NodeCycle first = new DetectionOfCycle.NodeCycle<>(1);
        DetectionOfCycle.NodeCycle two = new DetectionOfCycle.NodeCycle<>(2);
        first.next = two;
        two.next = null;
        boolean res = DetectionOfCycle.hasCycle(first);
        assertThat(res, is(false));
    }

    @Test
    public void whenLocalCycleThenReturnTrue() {
        DetectionOfCycle.NodeCycle first = new DetectionOfCycle.NodeCycle<>(5);
        DetectionOfCycle.NodeCycle two = new DetectionOfCycle.NodeCycle<>(2);
        DetectionOfCycle.NodeCycle third = new DetectionOfCycle.NodeCycle<>(3);
        DetectionOfCycle.NodeCycle four = new DetectionOfCycle.NodeCycle<>(4);
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = null;
        boolean res = DetectionOfCycle.hasCycle(first);
        assertThat(res, is(true));
    }

}