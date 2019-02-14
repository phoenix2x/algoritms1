/**
 */

//import edu.princeton.cs.algs4.StdDraw;
import io.vavr.API;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class HelloWorld {
    public static void main (String[] args) {
//        StdDraw.setScale(-1, 1);
//        StdDraw.clear(StdDraw.BLACK);
//
//        StdDraw.setPenColor(StdDraw.WHITE);
//        StdDraw.square(0, 0, 1);
//
//        // write some text
//        StdDraw.setPenColor(StdDraw.WHITE);
//        StdDraw.text(0, +0.95, "Hello, world! This is a test Java program.");
//        StdDraw.text(0, -0.95, "Close this window to finish the installation.");
//
//        // draw the bullseye
//        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
//        StdDraw.filledCircle(0, 0, 0.9);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.filledCircle(0, 0, 0.8);
//        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
//        StdDraw.filledCircle(0, 0, 0.7);
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.filledCircle(0, 0, 0.6);
//
//        // draw a picture of the textbook
//        StdDraw.picture(0, 0, "cover.jpg", 0.65, 0.80);
//        Map<String, Integer> map1 = HashMap.of("1", 1, "2", 2);
//        Map<String, Integer> map2 = HashMap.of("1", 11, "2", 22);
//        API.For(map1, map2)
//                .yield((entry1, entry2) -> Tuple.of(entry1._2(), entry2._2()))
//                .forEach(entry -> System.out.println(entry));
//        Test1 test1 = new Test1();

        int[] arr = new int[]{1,2,3};
        int[] arr2 = arr.clone();
        System.out.println(arr.toString() + arr2.toString());
        System.out.println(Arrays.toString(arr) + Arrays.toString(arr2));


        BigDecimal b =  new BigDecimal("1.00");
        b.compareTo(b);

//        String.CASE_INSENSITIVE_ORDER

//                Integer.compare()

        Comparator<Test1> comparator = Comparator.comparingInt(Test1::getInt);

        BigDecimal one = new BigDecimal("1.0");

        BigDecimal eleven = new BigDecimal("10.0");

        BigDecimal oneEleventh = one.divide(eleven);

        System.out.println(oneEleventh);

        double a = 1.0/10.0;

        double result = 0.0;
        System.out.println(a);

        for (int i = 0; i < 10; i++) {
            result += a;
        }

        System.out.println(result);

        int d = Integer.MIN_VALUE - 1;
        System.out.println(d);
    }

//    private class Item<T> {
//        private T[] data;
//        public Item(int capacity) {
//            this.data = (T[]) new Object[capacity];
//        }
//    }

    public static class Test1 implements Cloneable{
//        protected String getSome() {
//            return "";
//        }

//        @Override
//        protected Test1 clone() throws CloneNotSupportedException {
//            return (Test1) super.clone();
//        }

        public int getInt() {
            return 1;
        }

        public Test1 getInstance() {
            return new Test1();
        }
    }

    public static class Test2 extends Test1 {
//        @Override
//        public String getSome() {
//            return super.getSome();
//        }

        @Override
        public synchronized Test2 getInstance() {
            return new Test2();
        }

    }
}
