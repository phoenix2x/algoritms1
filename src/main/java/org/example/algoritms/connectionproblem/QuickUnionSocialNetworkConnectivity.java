package org.example.algoritms.connectionproblem;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Given a social network containing n members and a log file containing m timestamps
 * at which times pairs of members formed friendships, design an algorithm to determine the earliest time
 * at which all members are connected (i.e., every member
 * is a friend of a friend of a friend ... of a friend).
 * Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
 * The running time of your algorithm should be m log n or better and use extra space proportional to n.
 */

public class QuickUnionSocialNetworkConnectivity implements UnionFind {
    public static final Logger LOGGER = Logger.getLogger(QuickUnionSocialNetworkConnectivity.class.getName());

    private int[] data;
    /**
     * saving tree size of the root in this position, when tree size equals
     * size of array, means all nodes has the same root - thus all connected
     */
    private int[] treeSize;

    public QuickUnionSocialNetworkConnectivity(int size) {
        data = new int[size];
        treeSize = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = i;
            treeSize[i] = 1;
        }
    }

    public QuickUnionSocialNetworkConnectivity union(int a, int b) {
        int[] rootA = findRoot(a);
        int[] rootB = findRoot(b);
        if(rootA[0] != rootB[0]) {
            if (rootA[1] < rootB[1]) {
                data[rootA[0]] = rootB[0];
                mergeTreeSize(rootA[0], rootB[0]);
            } else {
                data[rootB[0]] = rootA[0];
                mergeTreeSize(rootB[0], rootA[0]);
            }
        }
        return this;
    }

    private void mergeTreeSize(int from, int to) {
        treeSize[to] = treeSize[to] + treeSize[from];
    }

    public boolean isAllConnected() {
        return treeSize[findRoot(1)[0]] >= treeSize.length;
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
        return new int[]{id, depth};
    }
}
