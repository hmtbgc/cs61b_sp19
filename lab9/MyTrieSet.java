import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
    private static final int R = 128;
    private Node root;


    public MyTrieSet() {
        root = new Node();
    }

    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null!");
        }
        return get(key) != null;
    }

    private Node get(String key) {
        return get(root, key, 0);
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }


    @Override
    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is null!");
        }
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.isKey = true;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = add(x.next[c], key, d+1);
        return x;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> x = new ArrayList<>();
        Node n = get(root, prefix, 0);
        for (char c = 0; c < R; ++c) {
            colHelp(prefix+c, x, n.next[c]);
        }
        return x;
    }

    private void colHelp(String s, List<String> x, Node n) {
        if (n == null) return;
        if (n.isKey) x.add(s);
        for (char c = 0; c < R; ++c) {
            colHelp(s+c, x, n.next[c]);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("this operation is not supported!");
    }


    private class Node {
        public boolean isKey;
        public Node[] next;

        public Node() {
            this.isKey = false;
            this.next = new Node[R];
        }
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
        System.out.println(t.contains("hello"));
        System.out.println(t.contains("hi"));
        System.out.println(t.contains("help"));
        System.out.println(t.contains("zebra"));
        t.add("helo");
        t.add("hepo");
        t.add("heuck");
        System.out.println(t.keysWithPrefix("he"));
    }

}
