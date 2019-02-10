import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds; // all calculated thresholds

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("grid size and trials must be above 0");
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++)
            singleTrial(n, i);
    }

    // a single run of monte carlo experiment
    private void singleTrial(int n, int trial) {
        Percolation perc = new Percolation(n);
        while (!perc.percolates()) {
            int row = StdRandom.uniform(1, n+1);
            int column = StdRandom.uniform(1, n+1);
            perc.open(row, column);
        }
        double gridSize = n*n;
        double threshold = perc.numberOfOpenSites() / gridSize;
        thresholds[trial] = threshold;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96*stddev() / Math.sqrt(thresholds.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96*stddev() / Math.sqrt(thresholds.length);
    }

    // test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        System.out.println("mean: " + stats.mean());
        System.out.println("stddev: " + stats.stddev());
        System.out.println(
                "95% confidence interval: ["+stats.confidenceLo()+
                        ","+stats.confidenceHi()+"]"
        );
    }
}