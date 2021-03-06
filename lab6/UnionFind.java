public class UnionFind {

    private int[] id;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= id.length) {
            throw new IllegalArgumentException("Index is out of range!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return -id[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return id[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        return root1 == root2;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 != root2) {
            if (sizeOf(root1) > sizeOf(root2)) {
                id[root1] -= sizeOf(root2);
                int i = v2;
                int p = parent(i);
                while (p >= 0) {
                    id[i] = root1;
                    i = p;
                    p = parent(i);
                }
                id[root2] = root1;

            } else {
                id[root2] -= sizeOf(root1);
                int j = v1;
                int q = parent(j);
                while (q >= 0) {
                    id[j] = root2;
                    j = q;
                    q = parent(j);
                }
                id[root1] = root2;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        while (parent(vertex) >= 0) {
            vertex = parent(vertex);
        }
        return vertex;
    }

    public static void main(String[] args) {
        UnionFind q = new UnionFind(7);
        q.union(0, 1);
        q.union(2, 3);
        q.union(2, 4);
        q.union(5, 6);
        q.union(6, 0);
        q.union(1, 4);

    }

}
