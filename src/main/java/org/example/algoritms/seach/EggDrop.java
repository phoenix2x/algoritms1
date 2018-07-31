package org.example.algoritms.seach;

/**
 * Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs.
 * An egg breaks if it is dropped from floor T or higher and does not break otherwise.
 * Your goal is to devise a strategy to determine the value of T given
 * the following limitations on the number of eggs and tosses:
 *
 * Version 1: 1 egg, ≤T tosses. - loop from first floor until egg breaks
 * Version 2: ∼1lgn eggs and ∼1lgn tosses. - binary search until egg breaks and there is no way down.
 * Version 3: ∼lgT eggs and ∼2lgT tosses. - trying to find approximate break going from bottom and doubling floor,
 *  after egg broken searching for exact break using previous algorithm.
 * Version 4: 2 eggs and ∼2 * sqrt(n) tosses. - solution to find approximate location using
 *  sqrt(n) increment - then iterate,
 *  then iterate from the lower bound with second egg.
 * Version 5: 2 eggs and ≤c * sqrt(T) tosses for some fixed constant c.
 *  - solution to find approximate location using sequence [1,3,6,10,15],
 *  then iterate from the lower bound with second egg.
 *
 *
 *  Hints:
 *
 * Version 0: sequential search.
 * Version 1: binary search.
 * Version 2: find an interval containing T of size ≤2T, then do binary search.
 * Version 3: find an interval of size n√, then do sequential search. Note: can be improved to ∼2n−−√ tosses.
 * Version 4: 1+2+3+…+t∼1/2pow(t,2). Aim for c=2sqrt(2).
 */

public class EggDrop {
    public static void main(String[] args) {
        Building building = new Building();
        int T = version4(building);
        System.out.println("T: " + T);
        System.out.println("Eggs broken: " + building.getEggsDropped());
        System.out.println("Tries: " + building.getTries());
    }
    private static int version5(Building building) {
        int startStory = 1;
        int increment = 2;
        int finishStory = building.getStories();

        return version5FindBreakFromBottom(building, startStory, finishStory, increment);
    }
    private static int version5FindBreakFromBottom(Building building, int startStory, int previousStart, int i) {
        System.out.println(startStory + " " + previousStart);
        if(building.isBreak(startStory)) {
            return version1Int(building, previousStart);
        }
        return version5FindBreakFromBottom(building, startStory + i, startStory, i + 1);
    }
    private static int version4(Building building) {
        int startStory = 1;
        int increment = (int) Math.sqrt(building.getStories());

        return version4FindBreakFromBottom(building, startStory, 0, increment);
    }
    private static int version4FindBreakFromBottom(Building building, int startStory, int previousStart, int increment) {
        System.out.println(startStory + " " + previousStart);
        if(building.isBreak(startStory)) {
            return version1Int(building, previousStart);
        }
        return version4FindBreakFromBottom(building, startStory + increment, startStory, increment);
    }
    private static int version3(Building building) {
        int startStory = 1;
        int finishStory = building.getStories();

        return version3FindBreakFromBottom(building, startStory, finishStory);
    }
    private static int version3FindBreakFromBottom(Building building, int startStory, int previousStart) {
//        System.out.println(startStory + " " + previousStart);
        if(building.isBreak(startStory)) {
            return version2Int(building, previousStart, startStory);
        }
        return version3FindBreakFromBottom(building, startStory * 2, startStory);
    }

    private static int version1(Building building) {
        return version1Int(building, 0);
    }
    private static int version1Int(Building building, int startStory) {
        for(int i = startStory; i < building.getStories(); i++) {
            if(building.isBreak(i)) {
                return i;
            }
        }
        return -1;
    }

    private static int version2(Building building) {
        int startStory = 0;
        int finishStory = building.getStories();

        return version2Int(building, startStory, finishStory);
    }
    private static int version2Int(Building building, int startStory, int finishStory) {
        int middle = (startStory + finishStory) / 2;
//        System.out.println(startStory + " " + finishStory + " " + middle);
//        if(middle == startStory || middle == finishStory) {
//            return middle;
//        }
        boolean broken = building.isBreak(middle);
        if(broken) {
            if(middle == startStory) {
                return middle;
            }
            return version2Int(building, startStory, middle);
        }
        if(middle == startStory) {
            return version2Int(building, finishStory, finishStory);
        }
        return version2Int(building, middle, finishStory);
    }

    private static class Building {
        private int stories = 100000;
        private int treshold = 10000;
        private int eggsDropped = 0;
        private int tries = 0;

        public int getStories() {
            return stories;
        }

        public int getEggsDropped() {
            return eggsDropped;
        }

        public int getTries() {
            return tries;
        }

        public boolean isBreak(int story) {
            this.tries += 1;
            boolean broken = story >= treshold;
            if(broken) {
                this.eggsDropped += 1;
            }
            return broken;
        }
    }
}
