package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.lang.IllegalArgumentException;
public class PercolationStats {

    private double[] fraction;
    private int number;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N <= 0 or T <= 0!");
        }

        fraction = new double[T];
        number = T;
        for (int i = 0; i < T; ++i) {
            int percolationNumber = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (p.isOpen(row, col)) {
                    p.open(row, col);
                    percolationNumber += 1;
                }
            }
            fraction[i] = (double) percolationNumber / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(fraction);
    }
    public double stddev() {
        return StdStats.stddev(fraction);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(number);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(number);
    }
}