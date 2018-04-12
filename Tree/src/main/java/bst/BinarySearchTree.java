package bst;

import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {

    BinaryNode<E> root;

    public BinarySearchTree(E e) {
        root = new BinaryNode<>(null, e);
    }

    public void add(E e) {
        root.addChild(e);
    }

    @Override
    public Iterator<E> iterator() {
        return new BstIterator();
    }

    /**
     * Производит поиск заданного элемента.
     * При первом совпадении возвращает истину.
     * @param value
     * @return
     */
    public boolean find(E value) {
        BinaryNode<E> p = root;
        boolean exist = false;
        while (p != null) {
            if (p.getValue().equals(value)) {
                exist = true;
                break;
            } else {
                if (value.compareTo(p.getValue()) > 0) {
                    p = p.right;
                } else {
                    p = p.left;
                }
            }
        }
        return exist;
    }

    static final class BinaryNode<E extends Comparable<E>> {
        private final E value;
        private BinaryNode left = null;
        private BinaryNode right = null;
        private BinaryNode parent;

        BinaryNode(BinaryNode<E> parent, E value) {
            this.value = value;
            this.parent = parent;
        }

        public E getValue() {
            return this.value;
        }

        /**
         * Добавляет потомка в текущий узел.
         * @param e
         */
        private void addChild(E e) {
            BinaryNode<E> child;
            boolean toRight = e.compareTo(value) > 0;
            if (toRight) {
                child = this.right;
            } else {
                child = this.left;
            }
            if (child != null) {
                child.addChild(e);
            } else {
                BinaryNode<E> newChild = new BinaryNode<>(this, e);
                if (toRight) {
                    this.right = newChild;
                } else {
                    this.left = newChild;
                }
            }
        }
    }

    private class BstIterator implements Iterator<E> {
        List<E> linearTree = new ArrayList<>();
        Iterator<E> iter;

        public BstIterator() {
            passNode(root, true);
            iter = linearTree.iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public E next() {
            if (iter.hasNext()) {
                return iter.next();
            }
            throw new NoSuchElementException();
        }

        private void passNode(BinaryNode<E> current, boolean firstPass) {
            if (current != null) {
                passNode(current.left, true);
                linearTree.add(current.getValue());
                passNode(current.right, true);
            }
        }
    }
}
