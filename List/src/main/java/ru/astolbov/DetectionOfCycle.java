package ru.astolbov;

import java.util.HashSet;

public class DetectionOfCycle {

    /**
     *Определяет наличие замыканий в списке.
     * @param first first node
     * @return true if cycle exist
     */
    public boolean hasCycle(NodeCycle first) {
        boolean res = false;

        if (first != null && first.next != null && first.next.next != null) {
            NodeCycle fast = first.next.next;
            NodeCycle slow = first;
            do {
                if (fast == slow) {
                    res = true;
                    break;
                }
                if (fast.next == null) {
                    break;
                }
                fast = fast.next.next;
                slow = slow.next;
            } while (fast != null);
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
