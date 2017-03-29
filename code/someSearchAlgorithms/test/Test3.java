package someSearchAlgorithms.test;

import java.util.ArrayList;

/**
 * Created by computer on 2016/11/29.
 */
public class Test3 {
    {
        System.out.println(Math.round(1.49));
        AutoCloseable closeable = () -> {};
        System.out.println(closeable);
    }

    public static void main(String[] args) {
        LambdaExp exp = () -> {};
        System.out.println(exp.getClass());
        exp = new LambdaExp() {
            @Override
            public void accept() {

            }
        };
        System.out.println(exp.getClass());

        new ArrayList<>().forEach(o -> {});
    }

}
@FunctionalInterface
interface LambdaExp {
    void accept();
}
