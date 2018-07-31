package org.example.algoritms.connectionproblem;

import java.util.logging.Logger;

/**
 *
 *
 * union too slow
 *  union - O(n^2)
 *  init - O(n)
 *  find - O(1)
 */

public class QuickFind implements UnionFind {

    public static final Logger LOGGER = Logger.getLogger(QuickFind.class.getName());

    private int[] data;

    public QuickFind(int size) {
        data = new int[size];
        for(int i = 0; i < size; i++) {
            data[i] = i;
        }
    }

    public QuickFind union(int a, int b) {
        if(data[a] != data[b]) {
            merge(a, b);
        }
        return this;
    }


    public boolean isConnected(int a, int b) {
        return data[a] == data[b];
    }

    private void merge(int a, int b) {
        int oldIndex = data[a];
        int newIndex = data[b];
        LOGGER.info("merging: "+ a + ", " + b);
        for(int i = 0; i < data.length; i++) {
            if(data[i] == oldIndex) {
                data[i] = newIndex;
                LOGGER.info("Changed item: " + i + ", oldIndex :" + oldIndex + ", newIndex: " + newIndex);
            }
        }
    }
}
