package simple;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void testIterator() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(3, 5);
        tree.add(3, 6);
        tree.add(6, 7);
        tree.add(6, 8);
        for (Integer i : tree) {
            System.out.println(i);
        }
    }

    @Test
    public void whenTreeIsBinaryThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        tree.add(3, 6);
        tree.add(6, 7);
        tree.add(6, 8);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenTreeIsNotBinaryThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 5);
        tree.add(3, 6);
        tree.add(6, 7);
        tree.add(6, 8);
        tree.add(6, 9);
        assertFalse(tree.isBinary());
    }
}