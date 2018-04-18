package simple;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E element) {
        this.root = new Node<>(element);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean res = false;
        if (!findBy(child).isPresent()) {
            Optional<Node<E>> exist = findBy(parent);
            if (exist.isPresent()) {
                exist.get().add(new Node<>(child));
                res = true;
            }
        }
        return res;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator iterator() {
        return new TreeIterator<E>();
    }

    public boolean isBinary() {
        return isBinaryNode(root);
    }

    private boolean isBinaryNode(Node<E> node) {
        boolean res = false;
        if (node.leaves().size() < 3) {
            res = true;
            for (Node<E> current : node.leaves()) {
                res = isBinaryNode(current);
                if (!res) {
                    break;
                }
            }
        }
        return res;
    }

    private class TreeIterator<E extends Comparable<E>> implements Iterator<E> {

        //Список всех списков потомков
        private List<List<Node<E>>> allChild = new ArrayList<>();
        //Итератор по списку всех списков потомков
        Iterator<List<Node<E>>> itAll;
        //Итератор по текущему списку потомков
        Iterator<Node<E>> itCurrent;

        TreeIterator() {
            allChild.add(Arrays.asList((Node<E>) root));
            addChild((Node<E>) root);
            itAll = allChild.iterator();
            itCurrent = itAll.next().iterator();
        }

        /**
         * Рекурсивно заполняет список всех списков потомков
         * @param node
         */
        private void addChild(Node<E> node) {
            List<Node<E>> childOfNode = node.leaves();
            if (childOfNode.size() > 0) {
                allChild.add(childOfNode);
                for (Node<E> current : childOfNode) {
                    addChild(current);
                }
            }
        }

        /**
         * Проверяет текущий список потомков.
         * Если он кончился, делаем текущим следующий список потомков из общего списка.
         * @return
         */
        @Override
        public boolean hasNext() {
            boolean res = itCurrent.hasNext();
            if (!res) {
                if (itAll.hasNext()) {
                    itCurrent = itAll.next().iterator();
                    res = itCurrent.hasNext();
                }
            }
            return res;
        }

        @Override
        public E next() {
            if (hasNext()) {
                return itCurrent.next().getValue();
            }
            throw new NoSuchElementException();
        }
    }

}
