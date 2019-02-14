package org.example.algoritms.connectionproblem;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Successor with delete. Given a set of n integers S={0,1,...,n−1} and a sequence of requests of the following form:
 *
 * Remove x from S
 * Find the successor of x: the smallest y in S such that y≥x.
 * design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
 *
 * Solution: Data array contains references to previous and next elements
 */

public class SuccessorWithDelete {
    public static final Logger LOGGER = Logger.getLogger(SuccessorWithDelete.class.getName());

    private int[][] data;

    public SuccessorWithDelete(int size) {
        data = new int[size][2];
        for(int i = 0; i < size; i++) {
            data[i] = new int[]{i - 1, i + 1};
        }
    }

    public int delete(int x) {
        LOGGER.info(Arrays.deepToString(data));
        if(x == 0 || (x == data.length -1)) {
            LOGGER.info("cant removeMin first or last");
        } else {
            data[x - 1][1] = data[x][1];
            data[x + 1][0] = data[x][0];
        }
        return data[x][1];
    }
}
