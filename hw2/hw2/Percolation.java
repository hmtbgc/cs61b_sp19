package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

public class Percolation {

    public boolean[][] grid;
    public WeightedQuickUnionUF uf;
    private int size;
    private int numOfOpenSites;
    private int head;
    private int tail;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N <= 0!");
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        grid = new boolean[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                grid[i][j] = false;
            }
        }
        size = N;
        numOfOpenSites = 0;
        head = 0;
        tail = N * N + 1;
    }


    private void validate(int row, int col) {
        if (row > size || col > size || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException("row or col is out of range!");
        }
    }

    private int xyTo1D(int row, int col) {
        return row * size + col + 1;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOfOpenSites += 1;
            if (row == 0) {
                uf.union(head, xyTo1D(row, col));
            }
            if (row == size - 1) {
                uf.union(xyTo1D(row, col), tail);
            }
            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            if (row < size - 1 && isOpen(row + 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col), xyTo1D(row, col -  1));
            }
            if (col < size - 1 && isOpen(row, col + 1)) {
                uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }
    }


    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] == true;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(head, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }
    public boolean percolates()  {
        return uf.connected(head, tail);
    }

    public static void main(String[] args) {

    }
}
