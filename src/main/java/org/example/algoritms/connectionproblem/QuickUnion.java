package org.example.algoritms.connectionproblem;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * numbers in array represents index of the parent node,
 * union operation - changing value of one root to the index of other root
 *
 * tree can get to tall - worst case find is O(n)
 *
 *  union - O(n)
 *  init - O(n)
 *  find(worst) - O(n)
 */

public class QuickUnion implements UnionFind {
    public static final Logger LOGGER = Logger.getLogger(QuickUnion.class.getName());

    private int[] data;

    public QuickUnion(int size) {
        data = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    public QuickUnion union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);
        data[rootA] = rootB;
        LOGGER.info(String.valueOf(a) + " " + String.valueOf(b));
        LOGGER.info(Arrays.toString(data));
        return this;
    }


    public boolean isConnected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    private int findRoot(int id) {
        while(id != data[id]) {
            id = data[id];
        }
        LOGGER.info(Arrays.toString(data));
        LOGGER.info(String.valueOf(id));
        return id;
    }

}
