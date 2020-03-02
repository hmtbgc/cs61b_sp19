public class Test {

 private class IntNode {
 public int item;
 public IntNode next;
 public IntNode(int item, IntNode next) {
     this.item = item;
     this.next = next;
 }
 }

         private IntNode first;

        public void addFirst(int x) {
             first = new IntNode(x, first);
             }


    public void reverse() {
        if (first == null || first.next == null) {
            return;
        }

        IntNode ptr = first.next;
        first.next = null;

        while (ptr != null) {
            IntNode temp = ptr.next;
            ptr.next = first;
            first = ptr;
            ptr = temp;
        }
    }

    public void printList(){
            IntNode x = first;
            while (x != null) {
                System.out.print(x.item+" ");
                x = x.next;
            }
            System.out.println();
    }

    public static void main(String[] args){
            Test p = new Test();
            p.addFirst(10);
            p.addFirst(20);
            p.addFirst(30);
            p.printList();
            p.reverse();
            p.printList();

    }
}
