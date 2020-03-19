import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Stack;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>  {

    private class BST {
        public K key;
        public V value;
        public BST left;
        public BST right;
        public int size;

        public BST(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.size = 1;
        }
    }

    private BST root;
    public Set<K> set = new HashSet<>();

    public BSTMap() {

    }

    public BSTMap(K key, V value) {
        root = new BST(key, value);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public V get(K key) {
        BST node = get(root, key);
        if (node == null) {
            return null;
        }
        else {
            return node.value;
        }
    }

    private int sonNumber(BST root) {
        int ans = 0;
        if (root.left != null) {
            ans += 1;
        }
        if (root.right != null) {
            ans += 1;
        }
        return ans;
    }

    public BST get(BST root, K key) {
        if (root == null) {
            return null;
        }

        if (root.key.equals(key)) {
            return root;
        }

        else if (sonNumber(root) == 0) {
            return null;
        }

        else if (key.compareTo(root.key) < 0) {
            return get(root.left, key);
        }

        else {
            return get(root.right, key);
        }
    }


    @Override
    public int size() {
        return size(root);
    }

    public int size(BST root) {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public BST put(BST root, K key, V value) {
        if (root == null) {
            return new BST(key, value);
        }

        if (key.compareTo(root.key) == 0) {
            root.value = value;
        }

        else if (key.compareTo(root.key) < 0) {
            root.left = put(root.left, key, value);
        }
        else {
            root.right = put(root.right, key, value);
        }
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    public void printInOrder(BST root) {
        if (root == null) {
            return;
        }
        if (sonNumber(root) == 0) {
            System.out.print(root.key + " ");
        }
        else {
            printInOrder(root.left);
            System.out.print(root.key + " ");
            printInOrder(root.right);
        }
    }

    @Override
    public Set<K> keySet() {
        getKey(root);
        return set;
    }

    private void getKey(BST root) {
        if (root == null) {
            return;
        }
        getKey(root.left);
        set.add(root.key);
        getKey(root.right);
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Remove operation is not supported!");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Remove operation is not supported!");
    }

    private class BSTMapIterator implements Iterator<K> {

        private Stack<BST> stack = new Stack<>();

        public BSTMapIterator(BST root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            BST c = stack.pop();

            if (c.right != null) {
                BST temp = c.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return c.key;
        }

    }

    @Override
    public Iterator iterator() {
        return new BSTMapIterator(root);
    }

    public static void main(String[] args) {
        BSTMap<Integer, Integer> b = new BSTMap<>();
        b.put(1,1);
        b.put(2,4);
        b.put(4,16);
        b.put(3,8);
        b.printInOrder();
        Set<Integer> s = b.keySet();
        for (int i : s){shi
            System.out.println(i);
        }
        for (int i : b) {
            System.out.println(i);
        }
    }
}
