package someSearchAlgorithms.algorithms;

import someSearchAlgorithms.datamodel.MapSet;
import someSearchAlgorithms.datamodel.Node;
import someSearchAlgorithms.datamodel.Unit;

/**
 * Created by L on 2016/12/27.
 */
public class Solution {
    public static int[][] K = {
            {0, -1},
            {-1, 0},
            {0, 1},
            {1, 0},
    };
    public Node path;
    Unit src;
    Unit dest;
    MapSet mapSet;
    public Unit[] array;//path used by IDASS

    public Node destNode;
    public Node srcNode;

    public Solution(Unit src, Unit dest, MapSet mapSet) {
        this.src = src;
        this.dest = dest;
        this.mapSet = mapSet;
    }
}
