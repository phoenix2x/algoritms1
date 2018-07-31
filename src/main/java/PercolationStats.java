import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

/**
 */

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final int n;
    private final double mean;
    private final double stdDev;
    private final double[] threshold;

    public PercolationStats(int n, int trials) {
        if(trials <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }
        this.n = n;
        this.trials = trials;
        this.threshold = new double[trials];

        doTest();

        this.mean = StdStats.mean(this.threshold);
        this.stdDev = StdStats.stddev(this.threshold);
    }

    public double mean() {
        return this.mean;
    }

    public double stddev() {
        return this.stdDev;
    }

    public double confidenceLo() {
        return this.mean() - CONFIDENCE_95 * this.stddev() / Math.sqrt(this.trials);
    }

    public double confidenceHi() {
        return this.mean() + CONFIDENCE_95 * this.stddev() / Math.sqrt(this.trials);
    }

    public static void main(String[] args) {
        int n = 10;
        int tries = 100;
        if(args.length >= 1) {
            n = Integer.parseInt(args[0]);
        }
        if(args.length >= 2) {
            tries = Integer.parseInt(args[1]);
        }

        runOneSimulation(n, tries);
    }

    private static double runOneSimulation(int n, int tries) {
        Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(n, tries);

        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = ["
                + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
        return stopwatch.elapsedTime();
    }

    private void doTest() {
        for(int i = 0; i < this.trials; i++) {
            Percolation percolation = new Percolation(this.n);
            while(!percolation.percolates()) {
                int row = StdRandom.uniform(this.n);
                int col = StdRandom.uniform(this.n);
                percolation.open(row + 1, col + 1);
            }
            this.threshold[i] = (double) percolation.numberOfOpenSites() / (this.n * this.n);
        }
    }
}
