package someSearchAlgorithms.algorithms;

import someSearchAlgorithms.datamodel.MapSet;
import someSearchAlgorithms.datamodel.Node;
import someSearchAlgorithms.datamodel.Node2;
import someSearchAlgorithms.datamodel.Unit;
import someSearchAlgorithms.test.Test;

import java.util.*;

import static someSearchAlgorithms.datamodel.Unit.BLACK;

import static someSearchAlgorithms.datamodel.Unit.WHITE;

/**
 * Created by pc on 2017/2/17.
 */
@SuppressWarnings("Duplicates")
public class Search {
    @SuppressWarnings("Duplicates")
    public static void BFS(Solution solution) {
        //实际上，在没有障碍物的情况下，两个点中间的最短距离就是max(x,y)，x，y分别为这两点间的水平和垂直距离
        Queue<Node> us = new ArrayDeque<>();
        solution.srcNode = new Node(solution.src);
        us.offer(solution.srcNode);
        MapSet dataSet = solution.mapSet;
        while (!us.isEmpty()) {
            Node p = us.poll();
//            for (int i = -1; i <= 1; i++) {
//                for (int j = -1; j <= 1; j++) {
//                    if (i != 0 || j != 0) {
//                        int x = i + p.element.x;
//                        int y = j + p.element.y;
//                        if (x >= 0 && y >= 0 && x < dataSet2.HEIGHT && y < dataSet2.WIDTH) {
//                            Unit c = dataSet2.map[x][y];
//                            if (c.color == WHITE) {
//                                c.color = BLACK;
//                                Node m = new Node(c);
//                                m.depth = p.depth + 1;
//                                us.offer(m);
//                                m.parent = p;
//                                p.child.add(m);
//
//
//                                if (c == solution.dest) {
//                                    solution.path = m;
//                                    return;//find
//                                }
//                            }
//                        }
//                    }
//                }
//            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
//                    if (i == 0 && j != 0 || j == 0 && i != 0) {
                    if (((i ^ j) & 1) != 0) {
                        int x = i + p.element.x;
                        int y = j + p.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = dataSet.map[x][y];
                            if (c.color == WHITE) {
                                c.color = BLACK;
                                Node m = new Node(c);
                                m.depth = p.depth + 1;
                                us.offer(m);
                                m.parent = p;
                                p.child.add(m);
                                if (c == solution.dest) {
                                    solution.path = m;
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
                            Unit c = dataSet.map[x][y];
                            if (c.color == WHITE) {
                                c.color = BLACK;
                                Node m = new Node(c);
                                m.depth = p.depth + 1;
                                us.offer(m);
                                m.parent = p;
                                p.child.add(m);
                                if (c == solution.dest) {
                                    solution.path = m;
                                    return;//find
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public static void ASS(Solution solution) {
        LinkedList<Node2> us = new LinkedList<>();
        us.offer(new Node2(solution.src));
        solution.srcNode = us.peek();
        MapSet dataSet = solution.mapSet;
        while (!us.isEmpty()) {
            Node p = us.poll();
//            for (int i = -1; i <= 1; i++) {
//                for (int j = -1; j <= 1; j++) {
//                    if (i != 0 || j != 0) {
//                        int x = i + p.element.x;
//                        int y = j + p.element.y;
//                        if (x >= 0 && y >= 0 && x < dataSet2.HEIGHT && y < dataSet2.WIDTH) {
//                            Unit c = dataSet2.map[x][y];
//                            if (c.color == WHITE) {
//                                c.color = BLACK;
//                                Node m = new Node(c);
//                                m.depth = p.depth + 1;
//                                us.offer(m);
//                                m.parent = p;
//                                p.child.add(m);
//
//
//                                if (c == solution.dest) {
//                                    solution.path = m;
//                                    return;//find
//                                }
//                            }
//                        }
//                    }
//                }
//            }


            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 && j != 0) {
                        int x = i + p.element.x;
                        int y = j + p.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = dataSet.map[x][y];
                            if (c.color == WHITE) {
                                c.color = BLACK;
                                Node2 m = new Node2(c);
                                m.depth = p.depth + 1;
                                m.h = h(m.element, solution.dest);//O(1)
                                insertOrderByF(us, m);
                                m.parent = p;
                                p.child.add(m);
                                if (c == solution.dest) {
                                    solution.path = m;
                                    return;//find
                                }
                            }
                        }
                    }
                }
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
//                    if (i == 0 && j != 0 || j == 0 && i != 0) {
                    if (((i ^ j) & 1) != 0) {
                        int x = i + p.element.x;
                        int y = j + p.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = dataSet.map[x][y];
                            if (c.color == WHITE) {
                                c.color = BLACK;
                                Node2 m = new Node2(c);
                                m.depth = p.depth + 1;
                                m.h = h(m.element, solution.dest);//O(1)
                                insertOrderByF(us, m);
//                                us.offer(m);
                                m.parent = p;
                                p.child.add(m);
                                if (c == solution.dest) {
                                    solution.path = m;
                                    return;//find
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void insertOrderByF(LinkedList<Node2> us, Node2 x) {
        Iterator<Node2> each = us.iterator();
        int where = 0;
        while (each.hasNext()) {
            Node2 element = each.next();
            int f = x.depth + x.h;
            if (f <= element.depth + element.h) break;
            where++;
        }
//        System.out.println(us);
        us.add(where, x);
//        System.out.println(us);
    }

    public static void DBFS(Solution solution) {
        MapSet dataSet = solution.mapSet;
        Queue<Node> up = new ArrayDeque<>();
        Queue<Node> down = new ArrayDeque<>();

        solution.srcNode = new Node(solution.src);
        solution.destNode = new Node(solution.dest);

        up.offer(solution.srcNode);
        down.offer(solution.destNode);

        while (!up.isEmpty() && !down.isEmpty()) {


            Node u = up.poll();//从上往下
//            for (int i = -1; i <= 1; i++) {
//                for (int j = -1; j <= 1; j++) {
//                    if (i != 0 || j != 0) {
//                        int x = i + u.element.x;
//                        int y = j + u.element.y;
//                        if (x >= 0 && y >= 0 && x < dataSet2.HEIGHT && y < dataSet2.WIDTH) {
//                            Unit c = dataSet2.map[x][y];
//                            if (c.color == WHITE || c.color == BLACK) {
//                                for (Node v : down) {
//                                    if (v.element == c) {
//                                        reserve(u, v);
//                                        solution.path = solution.destNode;
//                                        return;
//                                        //find
//                                    }
//                                }
//                                if (c.color == WHITE) {
//                                    c.color = BLACK;
//                                    Node z = new Node(c);
//                                    z.depth = u.depth + 1;
//                                    u.child.add(z);
//                                    z.parent = u;
//                                    up.offer(z);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (((i ^ j) & 1) != 0) {
                        int x = i + u.element.x;
                        int y = j + u.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = dataSet.map[x][y];
                            if (c.color == WHITE || c.color == BLACK) {
                                for (Node v : down) {
                                    if (v.element == c) {
                                        reserve(u, v);
                                        solution.path = solution.destNode;
                                        return;
                                        //find
                                    }
                                }
                                if (c.color == WHITE) {
                                    c.color = BLACK;
                                    Node z = new Node(c);
                                    z.depth = u.depth + 1;
                                    u.child.add(z);
                                    z.parent = u;
                                    up.offer(z);
                                }
                            }
                        }
                    }
                }
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 && j != 0) {
                        int x = i + u.element.x;
                        int y = j + u.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = dataSet.map[x][y];
                            if (c.color == WHITE || c.color == BLACK) {
                                for (Node v : down) {
                                    if (v.element == c) {
                                        reserve(u, v);
                                        solution.path = solution.destNode;
                                        return;
                                        //find
                                    }
                                }
                                if (c.color == WHITE) {
                                    c.color = BLACK;
                                    Node z = new Node(c);
                                    z.depth = u.depth + 1;
                                    u.child.add(z);
                                    z.parent = u;
                                    up.offer(z);
                                }
                            }
                        }
                    }
                }
            }
            Node d = down.poll();//从下往上
//            for (int i = -1; i <= 1; i++) {
//                for (int j = -1; j <= 1; j++) {
//                    if (i != 0 || j != 0) {
//                        int x = i + d.element.x;
//                        int y = j + d.element.y;
//                        if (x >= 0 && y >= 0 && x < dataSet2.HEIGHT && y < dataSet2.WIDTH) {
//                            Unit c = dataSet2.map[x][y];
//                            if (c.color == WHITE || c.color == BLACK) {
//                                for (Node v : up) {
//                                    if (v.element == c) {
//                                        reserve(v, d);//包含单元c的节点已经被存在队列的v节点中
//                                        solution.path = solution.destNode;
//                                        return;
//                                        //find
//                                    }
//                                }
//                                if (c.color == WHITE) {
//                                    c.color = BLACK;
//                                    Node z = new Node(c);
//                                    z.depth = d.depth + 1;
//                                    d.child.add(z);
//                                    z.parent = d;
//                                    z.realParent = d;
//                                    down.offer(z);
//                                }
//
//                            }
//                        }
//                    }
//                }
//            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (((i ^ j) & 1) != 0) {
                        int x = i + d.element.x;
                        int y = j + d.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = dataSet.map[x][y];
                            if (c.color == WHITE || c.color == BLACK) {
                                for (Node v : up) {
                                    if (v.element == c) {
                                        reserve(v, d);//包含单元c的节点已经被存在队列的v节点中
                                        solution.path = solution.destNode;
                                        return;
                                        //find
                                    }
                                }
                                if (c.color == WHITE) {
                                    c.color = BLACK;
                                    Node z = new Node(c);
                                    z.depth = d.depth + 1;
                                    d.child.add(z);
                                    z.parent = d;
                                    z.realParent = d;
                                    down.offer(z);
                                }

                            }
                        }
                    }
                }
            }
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 && j != 0) {
                        int x = i + d.element.x;
                        int y = j + d.element.y;
                        if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                            Unit c = dataSet.map[x][y];
                            if (c.color == WHITE || c.color == BLACK) {
                                for (Node v : up) {
                                    if (v.element == c) {
                                        reserve(v, d);//包含单元c的节点已经被存在队列的v节点中
                                        solution.path = solution.destNode;
                                        return;
                                        //find
                                    }
                                }
                                if (c.color == WHITE) {
                                    c.color = BLACK;
                                    Node z = new Node(c);
                                    z.depth = d.depth + 1;
                                    d.child.add(z);
                                    z.parent = d;
                                    z.realParent = d;
                                    down.offer(z);
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private static void reserve(Node u, Node v) {
        v.depth = u.depth + 1;
        if (v.parent != null) reserve(v, v.parent);
        else{
            v.clearAllBlack();
        }
        v.parent = u;

        //extra

    /*    //clear BLACK
        System.out.println(v.child.size());
        for (Node x : v.child) {
            x.element.color = WHITE; 这只是一条路径上的
        }*/
    }

    public static void IDASS(Solution solution) {
        Unit src = solution.src;
        Unit dest = solution.dest;
        int limitDepth = h(src, dest);
        do {
            solution.array = new Unit[limitDepth];
        } while (!DFS(0, limitDepth++, src, dest, 0, solution.array, solution.mapSet));
    }

    public static boolean DFS(int depth, int limitDepth, Unit src, Unit dest, int n, Unit[] path, MapSet dataSet) {
        src.color = BLACK;
        if (src == dest) return true;
        if (depth + h(src, dest) <= limitDepth) for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (i != 0 || j != 0) {
                    int x = i + src.x;
                    int y = j + src.y;
                    if (x >= 0 && y >= 0 && x < dataSet.HEIGHT && y < dataSet.WIDTH) {
                        Unit u = dataSet.map[x][y];
                        if (u.color == WHITE) {
                            path[n] = u;
                            if (DFS(depth + 1, limitDepth, u, dest, n + 1, path, dataSet)) return true;
                        }
                    }
                }
        src.color = WHITE;
        return false;
    }

    public static int h(Unit src, Unit dest) {
        return Math.max(Math.abs(src.x - dest.x), Math.abs(src.y - dest.y));
    }
}
