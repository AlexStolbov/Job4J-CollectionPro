package ru.astolbov;

import java.util.HashSet;

public class DetectionOfCycle {

    /**
     *Определяет наличие замыканий в списке.
     * @param first first node
     * @return true if cycle exist
     */
    static boolean hasCycle(NodeCycle first) {
        HashSet<NodeCycle> passed = new HashSet<>();
        boolean res = false;
        for (NodeCycle current = first; current != null; current = current.next) {
            if (!passed.add(current)) {
                res = true;
                break;
            }
        }
        return res;
    }

    public static class NodeCycle<T> {
        T value;
        NodeCycle<T> next;

        NodeCycle(T value) {
            this.value = value;
            this.next = null;
        }
    }

}
