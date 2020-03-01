public class ArrayDeque<T> {
    public T[] array;
    public int size;
    public int head;
    public int tail;

    public ArrayDeque(){
        array = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        if (other.size >= 8) {
            array = (T[]) new Object[other.size];
        } else {
            array = (T[]) new Object[8];
        }
        System.arraycopy(other.array, 0, array, 0, other.size);
        size = other.size;
        head = 0;
        tail = size - 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int afterSize) {
        T[] temp = (T[]) new Object[afterSize];
        int i = head;
        int len = array.length;
        int step = 0;
        while (i != tail) {
            temp[step] = array[i];
            i = (i + 1) % len;
            step += 1;
        }
        temp[step] = array[tail];
        array = temp;
        head = 0;
        tail = size - 1;
    }

    public void addFirst(T item) {
        if (size < array.length) {
            if (head > 0) {
                head -= 1;
                array[head] = item;
            } else {
                head = array.length - 1;
                array[head] = item;
            }
            size += 1;
        } else {
            resize(array.length * 2);
            head = array.length - 1;
            array[head] = item;
            size += 1;
        }
    }

    public void addLast(T item) {
        if (size < array.length) {
            if (tail < array.length - 1){
                if (array[tail] != null)
                    tail += 1;
                array[tail] = item;
            } else {
                tail = 0;
                array[tail] = item;
            }
            size += 1;
        } else {
            resize(array.length * 2);
            tail += 1;
            array[tail] = item;
            size += 1;
        }
    }


    public void printDeque() {
        if (isEmpty()) {
            System.out.println("null");
            return;
        }
        if (head > tail) {
            int i = head;
            while (i != tail) {
                System.out.print(array[i]+" ");
                i = (i + 1) % array.length;
            }
            System.out.print(array[tail]);
            System.out.println();
        } else {
            for (int i = head; i <= tail; i++) {
                System.out.print(array[i]+" ");
            }
            System.out.println();
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T out = array[head];
        array[head] = null;
        head = (head + 1) % array.length;
        size -= 1;
        if ((float) size / (float) array.length < 0.25) {
            resize(array.length / 2);
        }
        return out;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T out = array[tail];
        array[tail] = null;
        if (tail == 0) {
            tail = array.length - 1;
        } else {
            tail -= 1;
        }
        size -= 1;
        if ((float) size / (float) array.length < 0.25) {
            resize(array.length / 2);
        }
        return out;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return array[(head + index) % array.length];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.printDeque();
        a.addFirst(10);
        a.addLast(20);
        a.printDeque();
        a.addFirst(30);
        a.addLast(40);
        a.printDeque();
        System.out.println(a.size());
        a.addLast(50);
        a.addLast(60);
        a.printDeque();
        a.addFirst(70);
        a.addLast(80);
        a.printDeque();
        System.out.println(a.size());
        a.addLast(90);
        a.addLast(100);
        a.printDeque();
        System.out.println(a.size());
        System.out.println(a.removeFirst());
        System.out.println(a.removeLast());
        System.out.println(a.removeFirst());
        System.out.println(a.removeLast());
        System.out.println(a.removeFirst());
        System.out.println(a.removeLast());
        System.out.println(a.removeFirst());
        System.out.println(a.removeLast());
        System.out.println(a.get(3));

        ArrayDeque<Integer> b = new ArrayDeque<>(a);
        b.printDeque();
        b.addLast(50);
        b.printDeque();
        a.printDeque();
        b.addLast(60);
        b.addFirst(70);
        b.addLast(60);
        b.addFirst(70);
        b.addLast(60);
        b.addFirst(70);
        b.printDeque();
        System.out.println(b.size());
        a.printDeque();
        System.out.println(b.removeLast());
        System.out.println(b.removeFirst());
        System.out.println(b.removeLast());
        System.out.println(b.removeFirst());
        System.out.println(b.get(3));

    }
}
