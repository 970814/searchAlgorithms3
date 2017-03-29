package someSearchAlgorithms.datamodel;

/**
 * Created by L on 2017/2/20.
 */
public class Node2 extends Node {
    public Node2(Unit u) {
        super(u);
    }

    public int h = 0;

    @Override
    public String toString() {
        return String.valueOf(depth + h);
    }
}
