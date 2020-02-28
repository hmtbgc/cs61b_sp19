public class SLList{
    private int size;
    private IntNode sentinel;

    public SLList(){
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public void addFirst(int value) {
        sentinel = new IntNode(value, sentinel);
        size += 1;

    }
    public int getFirst() {
        return sentinel.next.value;
    }

    public void addLast(int value) {
        IntNode p = sentinel;

        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(value, null);
        size += 1;
    }

    public int iterativeSize() {
        IntNode p = sentinel.next;
        int result = 0;
        while (p != null) {
            result += 1;
            p = p.next;
        }
        return result;
    }

    private int size(IntNode p) {
        if (p.next == null) {
            return 1;
        }
        return 1 + size(p.next);
    }

    public int size() {
        return size(sentinel) - 1;
    }

    public int cacheSize() {
        return size;
    }

    public static void print_list(SLList L){
        IntNode p = L.sentinel;
        while (p != null) {
            System.out.print(p.value+" ");
            p = p.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        SLList p = new SLList();
        p.addFirst(20);
        p.addLast(30);
        p.addFirst(40);
        p.addLast(70);
        print_list(p);
        System.out.println(p.size());
        System.out.println(p.cacheSize());
    }
}