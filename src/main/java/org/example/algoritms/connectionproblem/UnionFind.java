package org.example.algoritms.connectionproblem;

/**
 */

public interface UnionFind {
    default public UnionFind union(String operation) {
        String[] oper = operation.split(",");
        return union(Integer.valueOf(oper[0]), Integer.valueOf(oper[1]));
    }

    default public boolean isConnected(String pair) {
        String[] p = pair.split(",");
        return isConnected(Integer.valueOf(p[0]), Integer.valueOf(p[1]));
    }

    public UnionFind union(int a, int b);

    public boolean isConnected(int a, int b);
}
