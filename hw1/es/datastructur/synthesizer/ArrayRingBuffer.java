package es.datastructur.synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos, count;

        public ArrayRingBufferIterator() {
            pos = first;
            count = 0;
        }
        public boolean hasNext() {
            return count < fillCount();
        }

        public T next() {
            T out = rb[pos];
            pos = (pos + 1) % capacity();
            count += 1;
            return out;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }

        rb[last] = x;
        fillCount += 1;
        last = (last + 1) % capacity();
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T out = rb[first];
        fillCount -= 1;
        first = (first + 1) % capacity();
        return out;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        return rb[first];

    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    public ArrayRingBuffer<T> duplicate(ArrayRingBuffer<T> q) {
        ArrayRingBuffer<T> oCopy = new ArrayRingBuffer<>(q.capacity());
        System.arraycopy(q.rb, 0, oCopy.rb, 0, q.capacity());
        oCopy.first = q.first;
        oCopy.last = q.last;
        oCopy.fillCount = q.fillCount;
        return oCopy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (other.capacity() != this.capacity()) {
            return false;
        }
        if (other.fillCount() != this.fillCount()) {
            return false;
        }
        ArrayRingBuffer<T> oCopy = duplicate(other);
        ArrayRingBuffer<T> thisCopy = duplicate(this);
        while (!oCopy.isEmpty() && !thisCopy.isEmpty()) {
            if (!oCopy.dequeue().equals(thisCopy.dequeue())) {
                return false;
            }
        }
        return true;
    }
}

