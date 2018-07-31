package org.example.algoritms.connectionproblem;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
 *
 * Remove x from S
 * Find the successor of x: the smallest y in S such that y≥x.
 *
 */

public class QuickUnionSuccessorWithDelete implements UnionFind {
    public static final Logger LOGGER = Logger.getLogger(QuickUnionSuccessorWithDelete.class.getName());

    private int[] data;

    public QuickUnionSuccessorWithDelete(int size) {
        data = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    public QuickUnionSuccessorWithDelete union(int a, int b) {
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
            data[id] = data[data[id]];
            id = data[id];
        }
        LOGGER.info(Arrays.toString(data));
        LOGGER.info(String.valueOf(id));
        return new int[]{id, depth};
    }

}
