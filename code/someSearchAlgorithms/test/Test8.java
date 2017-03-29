package someSearchAlgorithms.test;

import someSearchAlgorithms.datamodel.Unit;

import static someSearchAlgorithms.algorithms.Search.h;

/**
 * Created by pc on 2016/12/10.
 */
public class Test8 {
    public static void main(String[] args) {
        int[][] r = new int[][]{
                {1, 2, 3, 4, 5, 6},//1
                {1, 3, 3, 4, 5, 6},//2
                {1, 2, 2, 4, 5, 6},//3
                //...
        };
        System.out.println(h(new Unit(1, 2), new Unit(0, 0)));
    }
}
