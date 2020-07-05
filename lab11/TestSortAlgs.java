import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(5);
        q.enqueue(3);
        q.enqueue(8);
        q.enqueue(2);
        q.enqueue(4);
        q.enqueue(6);
        q = QuickSort.quickSort(q);
        Queue<Integer> temp = q;
        int left = q.dequeue();
        while (!q.isEmpty()) {
            int right = q.dequeue();
            assertTrue(right >= left);
            left = right;
        }
    }

    @Test
    public void testMergeSort() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(5);
        q.enqueue(3);
        q.enqueue(8);
        q.enqueue(2);
        q.enqueue(4);
        q.enqueue(6);
        q = MergeSort.mergeSort(q);
        Queue<Integer> temp = q;
        int left = q.dequeue();
        while (!q.isEmpty()) {
            int right = q.dequeue();
            assertTrue(right >= left);
            left = right;
        }
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
