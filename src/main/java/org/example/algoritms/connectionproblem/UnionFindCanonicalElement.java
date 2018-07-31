package org.example.algoritms.connectionproblem;

import java.util.logging.Logger;

/**
 * Add a method find() to the union-find data type so that find(i)
 * returns the largest element in the connected component containing i.
 * The operations, union(), connected(), and find() should all take logarithmic time or better.
 *
 * Solution: in union operation always moving smaller index under bigger,
 * the biggest index is always root of the component.
 */

public class UnionFindCanonicalElement implements UnionFind {

    private int[] data;

    public UnionFindCanonicalElement(int size) {
        data = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = i;
        }
    }


    public UnionFind union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);
        if(rootA < rootB) {
            data[rootA] = rootB;
        } else {
            data[rootB] = rootA;
        }
        return this;
    }

    public int find(int i) {
        return findRoot(i);
    }


    public boolean isConnected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    private int findRoot(int id) {
        while(id != data[id]) {
            data[id] = data[data[id]];
            id = data[id];
        }
        return id;
    }
}
