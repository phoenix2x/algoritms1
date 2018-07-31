package org.example.algoritms.connectionproblem;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Weighted By Size also gives O(log n)
 *
 * numbers in array represents index of the parent node,
 * union operation - changing value of one root to the index of other root
 *
 * we making sure that small tree goes under large tree, this way tree can get too tall
 * 
 *  union - O(n)
 *  init - O(log n)
 *  find - O(log n)
 */

public class QuickUnionWeightedByDepth implements UnionFind {
    public static final Logger LOGGER = Logger.getLogger(QuickUnionWeightedByDepth.class.getName());

    private int[] data;

    public QuickUnionWeightedByDepth(int size) {
        data = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    public QuickUnionWeightedByDepth union(int a, int b) {
        int[] rootA = findRoot(a);
        int[] rootB = findRoot(b);
        if(rootA[1] < rootB[1]) {
            data[rootA[0]] = rootB[0];
        } else {
            data[rootB[0]] = rootA[0];
        }
        LOGGER.info(String.valueOf(a) + " " + String.valueOf(b));
        LOGGER.info(Arrays.toString(data));
        return this;
    }

    public boolean isConnected(int a, int b) {
        return findRoot(a)[0] == findRoot(b)[0];
    }

    private int[] findRoot(int id) {
        int depth = 0;
        while(id != data[id]) {
            depth++;
            id = data[id];
        }
        LOGGER.info(Arrays.toString(data));
        LOGGER.info(String.valueOf(id));
        return new int[]{id, depth};
    }

}
