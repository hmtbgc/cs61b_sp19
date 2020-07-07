package bearmaps.proj2ab;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> heap;
    private int size;
    private HashMap<T, Integer> keys;

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        size = 0;
        keys = new HashMap<>();
    }

    private void exchange(int index_1, int index_2) {
        PriorityNode temp_1 = heap.get(index_1);
        PriorityNode temp_2 = heap.get(index_2);
        heap.set(index_1, temp_2);
        heap.set(index_2, temp_1);

        keys.replace(temp_1.item, index_2);
        keys.replace(temp_2.item, index_1);
        heap.get(index_1).setIndex(index_1);
        heap.get(index_2).setIndex(index_2);

    }

    private void up(int index) {
        if (index == 0) {return;}
        int parentIndex = (index - 1) / 2;
        PriorityNode child = heap.get(index);
        PriorityNode parent = heap.get(parentIndex);
        if (child.getPriority() < parent.getPriority()) {
            exchange(index, parentIndex);
            if (parentIndex != 0) {
                up(parentIndex);
            }
        }
    }

    private void down(int index) {
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        PriorityNode parent = heap.get(index);
        if (leftChildIndex < heap.size()) {
            PriorityNode leftChild = heap.get(leftChildIndex);
            double leftPriority = leftChild.getPriority();
            if (rightChildIndex < heap.size()) {
                PriorityNode rightChild = heap.get(rightChildIndex);
                double rightPriority = rightChild.getPriority();
                if (leftPriority < rightPriority) {
                    if (leftPriority < parent.getPriority()) {
                        exchange(index, leftChildIndex);
                        down(leftChildIndex);
                    }
                }
                else {
                    if (rightPriority < parent.getPriority()) {
                        exchange(index, rightChildIndex);
                        down(rightChildIndex);
                    }
                }
            }
            else {
                if (leftPriority < parent.getPriority()) {
                    exchange(index, leftChildIndex);
                    down(leftChildIndex);
                }
            }
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("This item has been existed!");
        }
        PriorityNode node = new PriorityNode(item, priority);
        node.setIndex(size);
        heap.add(node);
        keys.put(item, size);
        size += 1;
        up(heap.size()-1);
    }

    @Override
    public boolean contains(T item) {
        return keys.containsKey(item);
    }



    @Override
    public T getSmallest() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("No item is existed!");
        }
        return heap.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("No item is existed!");
        }
        PriorityNode removeNode = heap.get(0);
        exchange(0, heap.size()-1);
        heap.remove(heap.size()-1);
        if (!heap.isEmpty())
            down(0);
        size -= 1;
        keys.remove(removeNode.item);
        return removeNode.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("This item is not existed!");
        }
        int nowIndex = keys.get(item);
        PriorityNode currentNode = heap.get(nowIndex);
        PriorityNode replacedNode = new PriorityNode(item, priority);
        heap.set(nowIndex, replacedNode);
        if (priority > currentNode.getPriority()) {
            down(nowIndex);
        } else if (priority < currentNode.getPriority()) {
            up(nowIndex);
        } else {
            return;
        }
    }

    public void printHeap() {
        for (PriorityNode node : heap) {
            System.out.print(node.item + " ");
        }
        System.out.println();
    }

    public void clear() {
        heap = new ArrayList<>();
        size = 0;
        keys = new HashMap<>();
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;
        private int index;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        int getIndex () {return index; }

        void setIndex (int index) {this.index = index; }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

}
