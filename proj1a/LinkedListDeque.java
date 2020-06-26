public class LinkedListDeque<T> {

    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(){
            item = null;
            next = null;
            prev = null;
        }

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    public Node sentinel;
    public int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        Node k = sentinel;
        Node s = other.sentinel.next;
        size = other.size();
        int i = 0;
        while (i < size) {
            Node p = new Node(s.item, k, null);
            k.next = p;
            k = k.next;
            s = s.next;
            i += 1;
        }
        k.next = sentinel;
        sentinel.prev = k;
    }



    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T item) {
        Node p = new Node(item, null, null);
        p.prev = sentinel;
        p.next = sentinel.next;
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    public void addLast(T item) {
        Node p = new Node(item, null, null);
        p.prev = sentinel.prev;
        p.next = sentinel;
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    public void printDeque() {
        if (size == 0){
            System.out.println("null");
        }
        Node p = sentinel.next;
        int i = 0;
        while (i < size) {
            System.out.print(p.item+" ");
            p = p.next;
            i += 1;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0){
            return null;
        }
        Node p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        p.next = null;
        p.prev = null;
        size -= 1;
        return p.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        p.next = null;
        p.prev = null;
        size -= 1;
        return p.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        int i = 0;
        while (i < index) {
            p = p.next;
            i += 1;
        }
        return p.item;
    }

    private T helper(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return helper(p.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        return helper(p, index);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        System.out.println(L.size());
        L.addFirst(10);
        L.addFirst(20);
        L.printDeque();
        L.addLast(30);
        L.addLast(40);
        L.printDeque();
        System.out.println(L.size());
        System.out.println(L.removeFirst());
        System.out.println(L.size());
        System.out.println(L.removeLast());
        System.out.println(L.size());
        L.printDeque();
        System.out.println(L.getRecursive(1));

        LinkedListDeque<Integer> M = new LinkedListDeque<>(L);
        M.printDeque();
        M.addLast(30);
        M.addLast(40);
        L.printDeque();
        M.printDeque();
        L.addFirst(40);
        L.printDeque();
        M.printDeque();
        LinkedListDeque<Integer> N = new LinkedListDeque<>();
        LinkedListDeque<Integer> Q = new LinkedListDeque<>(N);
        Q.addFirst(30);
        System.out.println(Q.removeFirst());
        Q.printDeque();
    }
}
