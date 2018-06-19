package ru.astolbov;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LinkedListContainerConcurrencyTest {
    @Test
    public void whenAddInTwoThreadsThenResultIsDouble() throws InterruptedException {
        LinkedListContainer<Integer> ll = new LinkedListContainer<>();
        int size = 1000;
        int countThreads = 2;
        Thread one = new ThreadTest(ll, size);
        Thread two = new ThreadTest(ll, size);
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(ll.getSize(), is(size * countThreads));
    }

    class ThreadTest extends Thread {
        LinkedListContainer<Integer> ll;
        int size;
        ThreadTest(LinkedListContainer<Integer> ll, int size) {
            this.ll = ll;
            this.size = size;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                ll.add(i);
            }
        }
    }

}