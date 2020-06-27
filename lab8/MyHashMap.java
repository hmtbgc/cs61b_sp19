import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private class Node {
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<ArrayList<Node>> hashTable;
    private int initialSize;
    private double loadFactor;
    private int numOfBucket;
    private int nextNumOfBucket;
    private Set<K> keyset;
    private int size;

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.numOfBucket = initialSize;
        this.keyset = new HashSet<>();
        this.hashTable = new ArrayList<>();
        for (int i = 0; i < numOfBucket; ++i) {
            hashTable.add(new ArrayList<>());
        }
        size = 0;
    }

    @Override
    public void clear() {
        hashTable = new ArrayList<>();
        numOfBucket = 16;
        loadFactor = 0.75;
        for (int i = 0; i < numOfBucket; ++i) {
            hashTable.add(new ArrayList<>());
        }
        keyset = new HashSet<>();
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return keyset.contains(key);
    }

    private int keyHashCode(K key, int num) {
        return Math.floorMod(key.hashCode(), num);
    }

    @Override
    public V get(K key) {
        int hashCode = keyHashCode(key, numOfBucket);
        ArrayList<Node> keyArray = hashTable.get(hashCode);
        for (int i = 0; i < keyArray.size(); ++i) {
            if (key.equals(keyArray.get(i).key)) {
                return keyArray.get(i).value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (! containsKey(key)) {
            keyset.add(key);
        }

        boolean flag = false;
        Node p = new Node(key, value);
        int hashCode = keyHashCode(key, numOfBucket);
        ArrayList<Node> keyArray = hashTable.get(hashCode);
        for (int i = 0; i < keyArray.size(); ++i) {
            if (key.equals(keyArray.get(i).key)) {
                flag = true;
                keyArray.get(i).value = value;
                break;
            }
        }
        if (! flag) {
            keyArray.add(p);
            size += 1;
        }

        if (size() >= numOfBucket * loadFactor) {
            resize(2 * numOfBucket);
        }

    }

    private void resize(int capacity) {
        ArrayList<ArrayList<Node>> newHashTable = new ArrayList<>();
        for (int i = 0; i < capacity; ++i) {
            newHashTable.add(new ArrayList<Node>());
        }
        nextNumOfBucket = capacity;
        for (K key : this) {
            int hashCode = keyHashCode(key, nextNumOfBucket);
            newHashTable.get(hashCode).add(new Node(key, get(key)));
        }
        numOfBucket = nextNumOfBucket;
        hashTable = newHashTable;
    }

    @Override
    public Set<K> keySet() {
        return keyset;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("remove operation is not supported!");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("remove operation is not supported!");
    }


    @Override
    public Iterator<K> iterator() {
        return keyset.iterator();
    }
}
