package bst;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void testAdd() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(10);
        Integer[] s = {3, 2, 4, 15, 11, 18};
        for (Integer i : s) {
            bst.add(i);
        }
        for (Integer i : s) {
            assertTrue(bst.find(i));
        }
    }

    @Test
    public void testIterator() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(1);
        List<Integer> li = new ArrayList<>();
        li.add(12);
        li.add(10);
        li.add(14);
        li.add(4);
        li.add(2);
        li.add(8);
        for (Integer i : li) {
            bst.add(i);
        }
        li.add(1);
        Collections.sort(li);
        int ch = 0;
        for (Integer i : bst) {
            //System.out.println(i);
            assertThat(i, is(li.get(ch++)));
        }
    }

}