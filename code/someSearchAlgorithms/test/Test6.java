package someSearchAlgorithms.test;

import java.util.Arrays;

/**
 * Created by L on 2016/12/5.
 */
public class Test6 {
    public static void main(String[] args) {
        int[][] arrays = new int[][]{
                {1280, 1024},
                {1280, 960},
                {1280, 800},
                {1280, 768},
                {1280, 720},
                {1280, 600},
                {1152, 864},
                {1024, 768},
                {800, 600},
        };
        for (int i = 0; i < arrays.length; i++) {
            int a = arrays[i][0];
            int b = arrays[i][1];
            System.out.println(Arrays.toString(arrays[i]));
            for (int j = 2; j < 10; j++) {
                if (a % j == 0 && b % j == 0) {
                    System.out.print("\t: " + j);
                }
            }
            System.out.println();
        }
    }
}
