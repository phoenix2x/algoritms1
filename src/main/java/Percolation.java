import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * 1. creating two virtual nodes at n-1 and n-2,
 * 2. connecting all top nodes with n-1 node,
 * 3. connecting all bottom nodes with n-2 node.
 */

public class Percolation {
    private final WeightedQuickUnionUF unionUF;
    private final int topVirtualNode;
    private final int bottomVirtualNode;
    private final int n;

    private boolean[][] data;
    private int numberOpen;

    public Percolation(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }
        this.numberOpen = 0;
        this.n = n;
        int nodesNumber = n * n;
        this.data = new boolean[n][n];
        this.unionUF = new WeightedQuickUnionUF(nodesNumber + 2);
        this.topVirtualNode = nodesNumber;
        this.bottomVirtualNode = nodesNumber + 1;
        for (int i = 0; i < n; i++) {
//            System.out.println("init union top: " + i);
            this.unionUF.union(this.topVirtualNode, i);
//            System.out.println("init union bottom: " + (this.bottomVirtualNode - (i + 2)));
            this.unionUF.union(this.bottomVirtualNode, this.bottomVirtualNode - (i + 2));
        }
    }

    public void open(int row, int col) {
        if(!isNumberValid(row - 1) || !isNumberValid(col - 1)) {
            throw new IllegalArgumentException("Error");
        }
        if(isBlocked(row - 1, col - 1)) {
            int rowFromZero = row - 1;
            int colFromZero = col -1;
//            System.out.println("opening: " + rowFromZero + " " + colFromZero);
            this.numberOpen++;
            this.data[row - 1][col - 1] = true;

            int current = getNodeNumber(rowFromZero, colFromZero);
            int top = rowFromZero - 1;
            int bottom = rowFromZero + 1;
            int left = colFromZero - 1;
            int right = colFromZero + 1;
            if(isNumberValid(top) && isOpenFromZero(top, colFromZero)) {
//                System.out.println("union top: " + current + " " + getNodeNumber(top, colFromZero));
                this.unionUF.union(current, getNodeNumber(top, colFromZero));
            }
            if(isNumberValid(bottom) && isOpenFromZero(bottom, colFromZero)) {
//                System.out.println("union bottom: " + current + " " + getNodeNumber(bottom, colFromZero));
                this.unionUF.union(current, getNodeNumber(bottom, colFromZero));
            }
            if(isNumberValid(left) && isOpenFromZero(rowFromZero, left)) {
//                System.out.println("union left: " + current + " " + getNodeNumber(rowFromZero, left));
                this.unionUF.union(current, getNodeNumber(rowFromZero, left));
            }
            if(isNumberValid(right) && isOpenFromZero(rowFromZero, right)) {
//                System.out.println("union right: " + current + " " + getNodeNumber(rowFromZero, right));
                this.unionUF.union(current, getNodeNumber(rowFromZero, right));
            }
        }
    }

    private boolean isOpenFromZero(int row, int col) {
        return this.data[row][col];
    }

    private boolean isBlocked(int row, int col) {
//        System.out.println("is Blocked: " + row + " " + col + " " + (this.data[row][col] == 0));
        return !this.data[row][col];
    }

    public boolean isOpen(int row, int col) {
        if(!isNumberValid(row - 1) || !isNumberValid(col - 1)) {
            throw new IllegalArgumentException("Error");
        }
        return this.data[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if(!isNumberValid(row - 1) || !isNumberValid(col - 1)) {
            throw new IllegalArgumentException("Error");
        }
        return this.isOpen(row, col) && this.unionUF.connected(this.topVirtualNode, getNodeNumber(row - 1, col - 1));
    }

    public int numberOfOpenSites() {
        return this.numberOpen;
    }

    public boolean percolates() {
        return this.unionUF.connected(this.topVirtualNode, this.bottomVirtualNode);
    }

    public static void main(String[] args) {
//        int n = 100;
//        int tries = n * n * n;
//        Percolation percolation = new Percolation(n);
//        for(int i = 0; i <= tries; i++) {
//            int row = StdRandom.uniform(n);
//            int col = StdRandom.uniform(n);
//            percolation.open(row, col);
//            if(percolation.percolates()) {
//                break;
//            }
//        }
        Percolation percolation = new Percolation(3);
//        percolation.open(1, 6);
//        percolation.open(2, 6);
//        percolation.open(3, 6);
//        percolation.open(4, 6);
//        percolation.open(5, 6);
////        System.out.println(percolation.percolates());
//        percolation.open(5, 5);
//        percolation.open(4, 4);
//        percolation.open(3, 4);
//        percolation.open(2, 4);
//        percolation.open(2, 3);
//        percolation.open(2, 2);
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        percolation.open(3, 1);
        percolation.isFull(3, 1);
        percolation.open(2, 1);
        percolation.open(1, 1);
////        System.out.println(percolation.percolates());
//        percolation.open(5, 4);
//        System.out.println(percolation.isFull(3, 3) + " " + percolation.isOpen(3, 3));
        System.out.println(percolation.percolates());
    }

    private int getNodeNumber(int row, int col) {
        return row * n + col;
    }

    private boolean isNumberValid(int n) {
        return n >= 0 && n < this.n;
    }
}
