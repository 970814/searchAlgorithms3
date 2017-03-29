package someSearchAlgorithms.datamodel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static someSearchAlgorithms.datamodel.Unit.*;

/**
 * Created by computer on 2016/11/22.
 */

public class PathTree {


    public void clearBlack() {
        clearBlack(root);
    }

    private void clearBlack(Node node) {
        if (node != null) {
            node.element.color = WHITE;
            node.child.forEach(this::clearBlack);
        }
    }

    public static class Node {
        public Node parent = null;
        public Unit element;
        //        Node[] child = new Node[8];//u,d,l,r,ul,ur,dl,dr
//        int last;
        public ArrayList<Node> child = new ArrayList<>(8);
        public Node(Unit u) {
            element = u;
        }
    }

    public Node root;

    public PathTree(Unit u, MapSet mapSet) {
        this(new Node(u), mapSet);
    }

    MapSet dataSet;
    public PathTree(Node root, MapSet mapSet) {
        dataSet = mapSet;
        this.root = root;
    }

    public void getPath(MapSet mapSet) {
        for (int i = 0; i < dataSet.HEIGHT; i++) {
            for (int j = 0; j < dataSet.WIDTH; j++) {
                if (i == 0 || i == dataSet.HEIGHT - 1 || j == 0 || j == dataSet.WIDTH - 1) {
//                    PathTree tree = BFS(i, j);
//                    if (tree != null) {
//                        queue.add(tree);
//                    }
                }
            }
        }
    }

    //    public void BFS(int h, int w) {
//        BFS(dataSet2.map[h][w]);
//    }

    @SuppressWarnings("Duplicates")
    private static class LinkedNode {
        int D;
        LinkedNode previous;
        LinkedNode next;

        public LinkedNode(int d) {
            D = d;
            previous = this;
            next = this;
        }

        public LinkedNode(int d, LinkedNode previous, LinkedNode next) {
            D = d;
            this.previous = previous;
            this.next = next;
        }

        public static LinkedNode getLinked(int... ds) {
            LinkedNode first = new LinkedNode(ds[0]);
            first.next = first;
            first.previous = first;
            LinkedNode p = first;
            for (int i = 1; i < ds.length; i++) {
                p.next = new LinkedNode(ds[i], p, first);
                first.previous = p.next;
                p = p.next;
            }
            return first;
        }

        LinkedNode search(int d) {
            if (d == D)
                return this;
            else return next.search(d);
        }
    }
    @SuppressWarnings("Duplicates")
    public void goTo(Unit dest) {
        Unit[][] map = dataSet.map;
//        Unit source = root.element;
        Queue<Node> us = new LinkedList<>();
        us.offer(root);
        Node p;
        while (!us.isEmpty()) {
            p = us.poll();
            int i;
            int j;
            int location = dest.relativeTo(p.element);
            LinkedNode list = LinkedNode.getLinked(UpLeft, Up, UpRight, Right, DownRight, Down, DownLeft, Left).search(location);
            LinkedNode next = list.next;
            LinkedNode previous = list.previous;
            Queue<Integer> dQueue = new LinkedList<>();
            dQueue.offer(list.D);
            while (next != previous) {
                dQueue.offer(next.D);
                dQueue.offer(previous.D);
                next = next.next;
                previous = previous.previous;
            }
            dQueue.offer(next.D);
            for (Integer d : dQueue) {
                i = 0;
                j = 0;
                //noinspection UnnecessaryUnboxing
                switch (d.intValue()) {
                    case UpLeft:
                        i--;
                        j--;
                        break;
                    case Up:
                        i--;
                        break;
                    case UpRight:
                        i--;
                        j++;
                        break;
                    case Right:
                        j++;
                        break;
                    case DownRight:
                        i++;
                        j++;
                        break;
                    case Down:
                        i++;
                        break;
                    case DownLeft:
                        i++;
                        j--;
                        break;
                    case Left:
                        j--;
                        break;
                    default:
                        throw new RuntimeException();
                }
                if (i != 0 || j != 0) {
                    int x = i + p.element.x;
                    int y = j + p.element.y;
                    if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                        Unit c = map[x][y];
                        if (c.color == WHITE) {
                            c.color = BLACK;
                            Node m = new Node(c);
                            us.add(m);
                            m.parent = p;
                            p.child.add(m);
                            if (c == dest) {
                                return;//find
                            }
                        }
                    }
                }
            }
        }
    }
    @SuppressWarnings("Duplicates")
    public void goTo0(Unit dest) {
        Unit[][] map = dataSet.map;
        Queue<Node> us = new ArrayDeque<>();
        us.offer(root);
        Node p;
        while (!us.isEmpty()) {
            p = us.poll();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
//                    if (i == 0 && j != 0 || j == 0 && i != 0) {
                    if (((i ^ j) & 1) != 0) {
                        int x = i + p.element.x;
                        int y = j + p.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = map[x][y];
                            if (c.color == WHITE) {
                                c.color = BLACK;
                                Node m = new Node(c);
                                us.offer(m);
                                m.parent = p;
                                p.child.add(m);
                                if (c == dest) {
                                    return;//find
                                }
                            }
                        }
                    }
                }
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 && j != 0) {
                        int x = i + p.element.x;
                        int y = j + p.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = map[x][y];
                            if (c.color == WHITE) {
                                c.color = BLACK;
                                Node m = new Node(c);
                                us.offer(m);
                                m.parent = p;
                                p.child.add(m);
                                if (c == dest) {
                                    return;//find
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    @SuppressWarnings("Duplicates")
    public void BFS0() {
        Unit[][] map = dataSet.map;
        Queue<Node> us = new LinkedList<>();
        us.offer(root);
        Node p;
        while (!us.isEmpty()) {
            p = us.poll();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 || j == 0) {
                        int x = i + p.element.x;
                        int y = j + p.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = map[x][y];
                            if (c.color == WHITE) {
                                c.color = BLACK;
                                Node m = new Node(c);
                                us.add(m);
                                m.parent = p;
                                p.child.add(m);
                            }
                        }
                    }
                }
            }
        }
    }



    @SuppressWarnings("Duplicates")
    @Deprecated
    public void BFS() {
        Unit[][] map = dataSet.map;
        Queue<Node> us = new LinkedList<>();
        us.offer(root);
        Node p;
        while (!us.isEmpty()) {
            p = us.poll();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        int x = i + p.element.x;
                        int y = j + p.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = map[x][y];
                            if (c.color == WHITE) {
                                c.color = BLACK;
                                Node m = new Node(c);
                                us.add(m);
                                m.parent = p;
                                p.child.add(m);
                            }
                        }
                    }
                }
            }
        }
    }

    public Node search(Unit u) {
        return search(root, u);
    }

    private Node search(Node node, Unit u) {
        if (node != null) {
            if (node.element == u)
                return node;
            for (Node n : node.child) {
                Node r = search(n, u);
                if (r != null)
                    return r;
            }
        }
        return null;
    }
}
