package someSearchAlgorithms.graphics;

import someSearchAlgorithms.algorithms.Search;
import someSearchAlgorithms.datamodel.MapSet;
import someSearchAlgorithms.datamodel.Node;
import someSearchAlgorithms.datamodel.PathTree;
import someSearchAlgorithms.datamodel.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.*;

import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import static someSearchAlgorithms.datamodel.MapSet.showLocation;
import static someSearchAlgorithms.datamodel.MapSet.showPath;
import static someSearchAlgorithms.datamodel.Unit.GRAY2;
import static someSearchAlgorithms.datamodel.Unit.WHITE;
import static someSearchAlgorithms.graphics.GraphicsPainter.delay;

/**
 * Created by L on 2016/11/20.
 */
public class Window extends JFrame {
    private boolean releaseFlag = true;//MainWindow

    public double getProportion() {
        return proportion;
    }

    Object lock = new Object();
    public static Object sLock = new Object();
    Window game = this;

    double proportion;
    int Width;
    int Height;
    Unit heroLocation;
    MapSet dataSet;
    GraphicsPainter graphicsPainter;
    boolean has = false;
    public static PathTree pathTree;
    public static Queue<Node> nodeQueue = new ArrayDeque<>();
    public static Queue<Node> nodeQueue2 = new ArrayDeque<>();
     {
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        sleep(10);
//                        if (solution != null && solution.srcNode != null) {
//
//                            nodeQueue.offer(solution.srcNode);
//                            if (solution.destNode != null) {
//                                nodeQueue2.offer(solution.destNode);
//                            }
//
//                            do {
//                                synchronized (sLock) {
//                                    if (nodeQueue.peek().child != null) {
//                                        nodeQueue
//                                                .poll()
//                                                .child
//                                                .forEach(node -> nodeQueue.offer(node));
//                                    }
//                                    if (!nodeQueue2.isEmpty() && nodeQueue2.peek().child != null) {
//                                        nodeQueue2
//                                                .poll()
//                                                .child
//                                                .forEach(node -> nodeQueue2.offer(node));
//                                    }
//                                }
//                                sleep(8);
//                                game.repaint();
//                            } while (!nodeQueue.isEmpty());
//                        }
//
//                    } catch (Throwable e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
    }

    //    JPopupMenu popupMenu = new JPopupMenu();
    public Window(int WIDTH, int HEIGHT, double proportion) throws HeadlessException {
        Width = WIDTH;
        Height = HEIGHT;
        {
            setAlwaysOnTop(true);
            setSize(Width, Height);
            setUndecorated(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//            setLocationRelativeTo(null);
        }
        setContentPane(graphicsPainter = new GraphicsPainter(Height, Width, proportion));
        dataSet = graphicsPainter.getSet();
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
//                System.out.println(e.getX() + ", " + e.getY());
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }


            @Override
            public void mouseReleased(MouseEvent e) {
                Unit u = getUnit(e.getPoint());

                if (u != null && u.isSafe()) {//不会判断原来的位置，因为isSafe方法确保必须是白色或者黑色的点才会被响应

                    if (r[0] && (e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
                        r[0] = false;//现在不能在启动任何移动的线程
                        if (has && heroLocation.color != GRAY2) {
                            has = false;

                        }
                        if (has) {

                            r[1] = false;//中断原来的正在移动但又没移动完线程
                            while (!moveThreadQueue.isEmpty()) {//永远队列中只能存在一个移动线程
                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }

                            new MoveThread(u).start();



                        } else {
                            has = true;
                            heroLocation = getUnit(e.getPoint());
                            heroLocation.color = GRAY2;
                            r[0] = true;
                            repaint();
                        }
                    }
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
//                System.out.println(keyCode);
                switch (keyCode) {
                    case 10:
                        releaseFlag = !releaseFlag;
                        break;
                    case '1':
                        showPath = !showPath;
                        break;
                    case '2':
                        showLocation = !showLocation;
                        break;
                    case 'N':
                        for (int i = 0; i < 5; i++) {
                            dataSet.nextEnemy();
                        }
                        break;
                    case 'E':
                        graphicsPainter.paintPath(new PathTree(dataSet.getUnit(0, 0), dataSet) {{
                            BFS();
                        }});
                        break;
                    case 27://Esc
                        System.exit(0);

                        if (e.isShiftDown()) {
                            int choose = JOptionPane.showConfirmDialog(getParent()
                                    , "Are you sure you want to quit the game?"
                                    , "Exit Game?"
                                    , YES_NO_CANCEL_OPTION);
                            if (choose == 0)
                                System.exit(0);
                        }
                    default:
                        //ignore
                        return;
                }
                repaint();
            }
        });
    }
    volatile boolean[] r = {true, false};
    Queue<MoveThread> moveThreadQueue = new LinkedList<>();


    public class MoveThread extends Thread {
        Point e;
        Unit u;
        public MoveThread(Point point) {
            e = point;
        }
        public MoveThread(Unit unit) {
            u = unit;
        }
        @Override
        public void run() {
            try {
                goTo2(u, r);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
/*            if (e == null) {
                goTo2(u, r);
//                goTo(u, r);
            } else {
                goTo(e, r);
            }*/

        }

        public void goTo2(Unit dest, boolean[] r) throws InterruptedException {
            solution = new someSearchAlgorithms.algorithms.Solution(heroLocation, dest, dataSet);
            dest.isLocation = true;
//            long start = System.nanoTime();
            Search.ASS(solution);
//            long end = System.nanoTime();
//            System.out.println(time(end - start));
//            repaint();
            Unit[] array = null;
            if (solution.array != null) {
                array = solution.array;//IDASS
                for (int i = 0; i < array.length; i++) {
                    array[i].isPath = true;
                    array[i].color = WHITE;
                }
            } else {//other search algorithms
                solution.srcNode.clearAllBlack();
                if (solution.path != null) {
                    array = new Unit[solution.path.depth];
                    Node x = solution.path;
                    for (int i = array.length - 1; i >= 0; i--) {
                        array[i] = x.element;
                        x.element.isPath = true;
                        x = x.parent;
                    }
                }
            }
            moveThreadQueue.offer(this);
            r[1] = true;
            r[0] = true;//now can move to new location
            if (array != null) {
                for (int i = 0; i < array.length && r[1]; i++) {
                    heroLocation.color = WHITE;
                    heroLocation = array[i];
                    heroLocation.color = GRAY2;
                    delay(0x10);
                    repaint();
                }
            }
            moveThreadQueue.poll();
            if (array != null) {//clear path
                for (int i = 0; i < array.length; i++) {
                    array[i].isPath = false;
                    delay(0x40);
                    repaint();
                }
            }
        }

        /**
         * use algorithms
         */
        public void searchDest(Unit dest) {
            pathTree = new PathTree(heroLocation, dataSet);
            dest.isLocation = true;
            pathTree.goTo0(dest);
            PathTree.Node node = pathTree.search(dest);

            while (node != null) {
                node.element.isPath = true;
                path.push(node.element);
                node = node.parent;
            }
        }

        Stack<Unit> path = new Stack<>();

        //    Scanner scanner = new Scanner(System.in);

        @Deprecated
        public void goTo(Unit dest, boolean[] r) {


//        scanner.nextLine();

            /*new Thread() {
                @Override
                public void run() {
                    try {
                        while (!releaseFlag) {
                            Thread.sleep(100);+
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dataSet2.toWhite(); //O(n^2)
                }
            }.start();*/
//            dataSet2.toWhite();

            searchDest(dest);
            pathTree.clearBlack();
//            node = null;//let gc
//            pathTree = null;//let gc
            repaint();

            if (!path.isEmpty()) {

                moveThreadQueue.offer(this);
                r[1] = true;
                r[0] = true;//now can move to new location
                run(r);
                moveThreadQueue.poll();
            } else {

                r[1] = true;
                r[0] = true;//now can move to new location
            }
        }

        @Deprecated
        public void goTo(Point newLocation, boolean[] r) {
            goTo(getUnit(newLocation), r);
        }


        private void run(boolean[] r) {
            for (int i = path.size() - 1; r[1] && i >= 0; i--) {
                heroLocation.color = WHITE;
                heroLocation = path.get(i);
                heroLocation.color = GRAY2;
                heroLocation.isTest = true;
                delay(0x10);
                repaint();
            }

            new Thread(){
                @Override
                public void run() {
                    moveThreadQueue.poll().clearPath();
                }
            }.start();
        }

        private void clearPath() {
            while (!path.empty()) {
                path.pop().isPath = false;
                delay(0x10);
                repaint();
            }
        }


    }




    public Unit getUnit(Point point) {
        float margin = graphicsPainter.MARGIN;
        Point2D point2D = new Point2D.Float(point.x - margin, point.y - margin);
        int y = (int) Math.round(point2D.getY() / margin);
        int x = (int) Math.round(point2D.getX() / margin);
//        if (y > 1) {
//            y-=1;
//        }
//        if (x > 1) {
//            x-=1;
//        }
        try {
            return dataSet.getUnit(y, x);
        } catch (ArrayIndexOutOfBoundsException e) {
//            System.err.println(e.getMessage());
//            e.printStackTrace();
            return null;
        }
    }

    public static someSearchAlgorithms.algorithms.Solution solution;
}
