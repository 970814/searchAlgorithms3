package someSearchAlgorithms.datamodel;

import java.util.ArrayList;

/**
 * Created by pc on 2017/2/18.
 */
public class Node {
    public int depth = 0;
    public Node parent = null;
    public Node realParent = null;
    public Unit element;
    public ArrayList<Node> child = new ArrayList<>(8);
    public Node(Unit u) {
        element = u;
    }

    public void clearAllBlack() {
        element.color = Unit.WHITE;
        for (Node x : child) {
            x.clearAllBlack();
        }
    }
}
