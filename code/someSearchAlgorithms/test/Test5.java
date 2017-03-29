package someSearchAlgorithms.test;

/**
 * Created by L on 2016/12/5.
 */
public class Test5 {
    public static void main(String[] args) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (((i ^ j) & 01) != 0) {
//                if (i == 0 && j != 0 || j == 0 && i != 0) {
                    System.out.println(i + ", " + j);
                }
            }
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 && j != 0) {
                    System.out.println(i + ", " + j);
                }
            }
        }

    }
}
