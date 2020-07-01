package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Stopwatch;

public class ArrayHeapMinPQTest {

    static ArrayHeapMinPQ<Integer> p = new ArrayHeapMinPQ<>();

    @Test
    public void testGetSmallest() {
        p.clear();
        p.add(3, 0.01);
        p.add(4, 0.1);
        p.add(5, 1.0);
        assertEquals((int)p.getSmallest(), 3);
        p.add(6, 0.005);
        assertEquals((int)p.getSmallest(), 6);
    }

    @Test
    public void testRemoveSmallest() {
        p.clear();
        p.add(1, 0.2);
        assertEquals((int)p.getSmallest(), 1);
        assertEquals((int)p.removeSmallest(), 1);
        p.add(1, 0.2);
        p.add(3, 0.01);
        p.add(4, 0.1);
        p.add(5, 1.0);
        p.add(6, 0.005);
        //p.printHeap();
        assertEquals((int)p.removeSmallest(), 6);
        //p.printHeap();
        assertEquals((int)p.removeSmallest(), 3);
        //p.printHeap();
        assertEquals((int)p.removeSmallest(), 4);
    }

    @Test
    public void testChangePriority() {
        p.clear();
        p.add(1, 0.2);
        p.add(3, 0.01);
        p.add(4, 0.1);
        p.add(5, 1.0);
        p.add(6, 0.005);
        p.changePriority(6, 1.2);
        assertEquals((int)p.removeSmallest(), 3);
        assertEquals((int)p.getSmallest(), 4);
        assertEquals((int)p.removeSmallest(), 4);
        assertEquals((int)p.removeSmallest(), 1);
        //p.printHeap();
        p.add(1, 0.3);
        //p.printHeap();
        p.add(2, 0.9);
        //p.printHeap();
        p.add(7, 1.5);
        //p.printHeap();
        assertEquals((int)p.getSmallest(), 1);
        assertEquals((int)p.removeSmallest(), 1);
        assertEquals(p.size(), 4);
        assertTrue(p.contains(7));
        assertTrue(p.contains(5));
        assertFalse(p.contains(1));
        //p.printHeap();
        p.changePriority(2, 1.3);
        assertEquals((int)p.getSmallest(), 5);
        p.changePriority(5, 2.0);
        assertEquals((int)p.getSmallest(), 6);
        p.printHeap();
        p.add(4, 1.4);
        assertEquals((int)p.getSmallest(), 6);
        p.printHeap();
        p.changePriority(4, 1.6);
        p.changePriority(6, 1.2);
        assertEquals((int)p.removeSmallest(), 6);
        p.printHeap();
    }
}


