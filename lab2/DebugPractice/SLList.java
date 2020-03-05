public class SLList{
    private int size;
    private IntNode sentinel;

    public SLList(){
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public void addFirst(int value) {
        IntNode input = new IntNode(value, null);
        input.next = sentinel.next;
        sentinel.next = input;
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

    public void insert(int item, int position) {
        if (position == 0) {
            addFirst(item);
        }
        else if (position > size - 1) {
            addLast(item);
        } else {
            IntNode p = sentinel;
            int i = 0;
            while (i < position) {
                p = p.next;
                i += 1;
            }
            IntNode input = new IntNode(item, null);
            input.next = p.next;
            p.next = input;
        }
        size += 1;
    }

    public void reverse() {
        IntNode p = sentinel.next.next;
        sentinel.next.next = null;
        while (p != null) {
            IntNode temp = p.next;
            p.next = sentinel.next;
            sentinel.next = p;
            p = temp;
        }
    }

    private IntNode recurisive(IntNode p){
        if (p.next == null || p == null) {
            return p;
        }
        IntNode temp = p.next;
        IntNode reversed = recurisive(p.next);
        temp.next = p;
        p.next = null;
        return reversed;
    }
    public void reverseRecurisive() {
        sentinel.next = recurisive(sentinel.next);
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
        IntNode p = L.sentinel.next;
        while (p != null) {
            System.out.print(p.value+" ");
            p = p.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        SLList p = new SLList();
        p.addFirst(20);
//        p.addLast(30);
//        p.addFirst(40);
//        p.addLast(70);
        print_list(p);
        System.out.println(p.size());
        System.out.println(p.cacheSize());
        p.insert(50, 1);
        print_list(p);
//        p.insert(60, 0);
//        p.insert(80, 100);
        p.insert(35, 4);
//        print_list(p);
        p.reverse();
        print_list(p);
        p.reverseRecurisive();
        print_list(p);
    }
}