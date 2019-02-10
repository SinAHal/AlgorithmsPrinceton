import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF ufStruct; // weighted quick union data structure
    private int gridRoot;
    private boolean[][] sites; // grid of nxn sites
    private int openCount; // total open sites

    // create (n*n)+2 WeightedQuickUnionUF data structure
    // row=1 col=x will all be connected to ufStruct[ 0 ] (virtual top)
    // row=n col=x will all be connected to ufStruct[N+1] (virtual bottom)
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "Grid dimensions must be greater than 0");
        }
        ufStruct = new WeightedQuickUnionUF((n*n)+2);
        initUFStruct(n);
        gridRoot = n;
        sites = new boolean[n+1][n+1];
        openCount = 0;
    }

    // intialises union-find data structure with false top and bottom as well
    private void initUFStruct(int n) {
        for (int i = 1; i <= n; i++)
            ufStruct.union(0, i);

        int last = n*n;
        int bottom = last+1;
        for (int i = last; i > (last)-n; i--) {
            ufStruct.union(bottom, i);
        }
    }

    // open site (row, col) if it is not open already
    // calls to union() on all adjacent OPEN sites
    public void open(int row, int col) {
        validateIndices(row, col);
        if (!isOpen(row, col)) {
            openCount += 1;
            sites[row][col] = true;
            connectNeighbours(row, col);
        }
    }

    // checks if neighbours are within boundaries and connects if they are open as well
    private void connectNeighbours(int row, int col) {
        int current = grid2UF(row, col);
        // left of site
        if (row != 1 && isOpen(row-1, col))
            ufStruct.union(current, grid2UF(row-1, col));
        // right of site
        if (row != gridRoot && isOpen(row+1, col))
            ufStruct.union(current, grid2UF(row+1, col));
        // above site
        if (col != 1 && isOpen(row, col-1))
            ufStruct.union(current, grid2UF(row, col-1));
        // below site
        if (col != gridRoot && isOpen(row, col+1))
            ufStruct.union(current, grid2UF(row, col+1));
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return sites[row][col];
    }

    // is site (row, col) full?
    // i.e. connected(0, row*col) == true
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return ufStruct.connected(0, grid2UF(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    // is top virtual site connected to bottom virtual site? connected(0, N*N+1
    public boolean percolates() {
        int bottom = (gridRoot * gridRoot) + 1;
        return ufStruct.connected(0, bottom);
    }

    // validates row and col parameters
    private void validateIndices(int row, int col) {
        if (row > gridRoot || col > gridRoot || row <= 0 || col <= 0) {
            throw new IllegalArgumentException(
                    "row and column indices must be between 1 and " + gridRoot);
        }
    }

    // converts row and col parameters to weighted union-find structure index
    private int grid2UF(int row, int col) {
        return ((row-1) * gridRoot) + col;
    }

    // Run a Monte Carlo simulation, args[0] is size of grid
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Percolation perc = new Percolation(n);
        while (!perc.percolates()) {
            int row = StdRandom.uniform(1, n+1);
            int column = StdRandom.uniform(1, n+1);
            perc.open(row, column);
        }
        double gridSize = n*n;
        double threshold = perc.numberOfOpenSites() / gridSize;
        System.out.println("Threshold is: "+threshold);
    }
}