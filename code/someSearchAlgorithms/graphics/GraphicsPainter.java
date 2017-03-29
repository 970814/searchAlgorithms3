package someSearchAlgorithms.graphics;

import someSearchAlgorithms.datamodel.MapSet;
import someSearchAlgorithms.datamodel.Node;
import someSearchAlgorithms.datamodel.PathTree;
import someSearchAlgorithms.datamodel.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

import static someSearchAlgorithms.datamodel.MapSet.bgColors;
import static someSearchAlgorithms.datamodel.Unit.*;

/**
 * Created by computer on 2016/11/21.
 */
@SuppressWarnings("Duplicates")
public class GraphicsPainter extends JComponent {
    MapSet dataSet;

//    public GraphicsPainter(MapSet mapSet) {
//        dataSet2 = mapSet;
//    }
    Queue<PathTree> queue = new LinkedList<>();



    public void addPath(PathTree path) {
        queue.offer(path);
        Unit last = dataSet.getUnit(HEIGHT - 1, WIDTH - 1);
        PathTree.Node node = path.search(last);
    }

    public void paintPath(PathTree path) {
        PathTree.Node root = path.root;
        new Thread(){
            @Override
            public void run() {
                System.out.println("running...");
                paint0(root);
                root.element.color = WHITE;
                System.out.println("stopped...");
            }
        }.start();
    }
    public void delay() {
//        delay(random.nextInt(100));
        delay(50);
    }
    public static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void paint0(PathTree.Node node) {//广度绘制
        node.element.color = GRAY;
        repaint();
        delay();
        node.element.color = WHITE;
        for (PathTree.Node child : node.child)
            new Thread() {
                @Override
                public void run() {
                    paint0(child);
                }
            }.start();
    }

    private void paint(PathTree.Node node) {//深度绘制
        node.element.color = GRAY;
        repaint();
        delay();
//        node.element.color = WHITE;
        for (PathTree.Node child : node.child) {
            paint(child);
            child.element.color = WHITE;
        }
    }

    Random random = new Random();

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D d = (Graphics2D) g;
        d.setColor(bgColors[3]);
        d.fill(new Rectangle2D.Float(0, 0, Width, Height));
        if (useNeatDrawing) {
            neatDrawing4(d);
        } else {
            autoSizeDrawing3(d);
        }

//        PathTree tree = Window.pathTree;
//        if (tree != null) {
//            PathTree.Node node = tree.root;
//            d.setColor(Color.RED);
//            drawPathLines(d, node);
//        }
//        drawPathSlow(d, Window.nodeQueue);
//        if (!queue.isEmpty()) {
//            PathTree pathTree = queue.peek();
//        }

/*        Solution solution = Window.solution;
        if (solution != null) {
            d.setColor(Color.BLUE);
            synchronized (sLock) {
                drawPathLines(d, solution.srcNode);
                drawPathLines(d, solution.destNode);

//                drawPathSlow2(d, Window.nodeQueue);
//                drawPathSlow2(d, Window.nodeQueue2);
            }
        }*/


    }
//    Queue<Node> nodeQueue = new ArrayDeque<>();

    /*{
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(2000);
                        if (Window.solution != null) {
                            nodeQueue.offer(Window.solution.srcNode);
                            while (!nodeQueue.isEmpty()) {
                                Node x = nodeQueue.poll();
                                for (Node z : x.child) {
                                    nodeQueue.offer(z);
                                }
                                sleep(200);
                                repaint();
                            }
                        }


                    } catch (Exception e) {

                    }
                }
            }
        }.start();
    }*/

    private void drawPathLines(Graphics2D d, Node node) {
        if (node != null) node.child.forEach(c -> {
            line2D.setLine(marginArray[node.element.y + 1], marginArray[node.element.x + 1]
                    , marginArray[c.element.y + 1], marginArray[c.element.x + 1]);
            d.draw(line2D);
            drawPathLines(d, c);
        });
    }
    private void drawPathSlow2(Graphics2D d, Queue<Node> nodeQueue) {
        nodeQueue.forEach(node ->
                node.child.forEach(c -> {
                    line2D.setLine(marginArray[node.element.y + 1], marginArray[node.element.x + 1]
                            , marginArray[c.element.y + 1], marginArray[c.element.x + 1]);
                    d.draw(line2D);
                })
        );
    }


    Line2D line2D = new Line2D.Double();
    @Deprecated
    private void drawPathSlow(Graphics2D d, Queue<PathTree.Node> nodeQueue) {
        nodeQueue.forEach(node ->
                node.child.forEach(c -> {
                    line2D.setLine(marginArray[node.element.y + 1], marginArray[node.element.x + 1]
                            , marginArray[c.element.y + 1], marginArray[c.element.x + 1]);
                    d.draw(line2D);
                })
        );
    }
    @Deprecated
    private void drawPathLines(Graphics2D d, PathTree.Node node) {
        if (node != null) node.child.forEach(c -> {
            line2D.setLine(marginArray[node.element.y + 1], marginArray[node.element.x + 1]
                    , marginArray[c.element.y + 1], marginArray[c.element.x + 1]);
            d.draw(line2D);
            drawPathLines(d, c);
        });
    }


    Rectangle2D mapBorder = new Rectangle2D.Float();
    int Width;
    int Height;
    final float marginX;
    final float marginY;
    final float MARGIN;
    final int X;
    final int Y;
    final Rectangle2D border;
    boolean useNeatDrawing;
    int baseX = 10;
    int baseY = 10;
    float[] marginArray;


    public GraphicsPainter(int height, int width, double proportion) {
        dataSet = new MapSet((int) (height / proportion), ((int) (width / proportion)), proportion);
        Width = width ;
        Height = height;

        X = dataSet.HEIGHT;
        Y = dataSet.WIDTH;
        useNeatDrawing = true;
        Width -= baseY * 2;
        Height -= baseX * 2;
        marginX = (float) Height / (X + 1);
        marginY = (float) Width / (Y + 1);
        MARGIN = Math.min(marginX, marginY);

        marginArray = new float[Math.max(X, Y) + 1];
        for (int i = 1; i < marginArray.length; i++) {
            marginArray[i] = MARGIN + marginArray[i - 1];
        }
        border = new Rectangle2D.Float(MARGIN / 2, MARGIN / 2, marginArray[Y], marginArray[X]);

    }

    @SuppressWarnings("Duplicates")
    public void neatDrawing5(Graphics2D d) {
        float x = baseX;
        float y;
        Unit[][] map = dataSet.map;
        //noinspection Duplicates
        for (Unit[] us : map) {
            x += MARGIN;
            y = baseY;
            for (Unit u : us) {
                y += MARGIN;
                u.toImage(d, y, x);
            }
        }
        d.setColor(Color.BLACK);
        d.draw(border);
    }

    public void neatDrawing4(Graphics2D d) {
        float x = 0;
        float y;
        Unit[][] map = dataSet.map;
        //noinspection Duplicates
        for (Unit[] us : map) {
            x += MARGIN;
            y = 0;
            for (Unit u : us) {
                y += MARGIN;
                u.toImage(d, y, x);
            }
        }
        d.setColor(Color.BLACK);
        d.draw(border);
    }


    public void neatDrawing3(Graphics2D d) {
        int H = getHeight();
        int W = getWidth();
        int X = dataSet.HEIGHT;
        int Y = dataSet.WIDTH;
        final float marginX = (float) H / (X + 1);
        final float marginY = (float) W / (Y + 1);
        final float MARGIN = Math.min(marginX, marginY);

        float x = 0;
        float y;
        Unit[][] map = dataSet.map;
        //noinspection Duplicates
        for (Unit[] us : map) {
            x += MARGIN;
            y = 0;
            for (Unit u : us) {
                y += MARGIN;
                u.toImage(d, y, x);
            }
        }
        float p = MARGIN / 2;
        mapBorder.setFrame(p, p, MARGIN * Y, MARGIN * X);
        d.setColor(Color.BLACK);
        d.draw(mapBorder);
    }

    public void autoSizeDrawing3(Graphics2D d) {
//        Font s = getFont().deriveFont(1.6f);
//        Font b = getFont().deriveFont(4.6f);
        int H = getHeight();
        int W = getWidth();
        int X = dataSet.HEIGHT;
        int Y = dataSet.WIDTH;
        float marginX = (float) H / (X + 1);
        float marginY = (float) W / (Y + 1);
        float x = 0;
        float y;
        Unit[][] map = dataSet.map;
        //noinspection Duplicates
        for (Unit[] us : map) {
            x += marginX;
            y = 0;
            for (Unit u : us) {
                y += marginY;
                u.toImage(d, y, x);
            }
        }
    }

    @Deprecated
    @SuppressWarnings("unused")
    public void neatDrawing2(Graphics2D d) {
//        System.out.println(d.hashCode());
        Font s = getFont().deriveFont(1.6f);
        Font b = getFont().deriveFont(4.6f);
        int H = getHeight();
        int W = getWidth();
        int X = dataSet.HEIGHT;
        int Y = dataSet.WIDTH;
        float marginX = (float) H / (X + 1);
        float marginY = (float) W / (Y + 1);
        float margin = Math.min(marginX, marginY);
        float x = 0;
        float y;
        Unit[][] map = dataSet.map;
        //noinspection Duplicates
        for (Unit[] us : map) {
            x += margin;
            y = 0;
            for (Unit u : us) {
                y += margin;
                switch (u.color) {
                    case GRAY:
//                        u.toImage(d, rectangle);
                        continue;
                    case ENEMY:
                        d.setFont(b);
                    default:
                        d.setFont(s);
                }
                d.drawString(u.toString(), y, x);
            }
        }
    }

    @Deprecated
    @SuppressWarnings("unused")
    public void autoSizeDrawing2(Graphics2D d) {
        Font s = getFont().deriveFont(1.6f);
        Font b = getFont().deriveFont(4.6f);
        int H = getHeight();
        int W = getWidth();
        int X = dataSet.HEIGHT;
        int Y = dataSet.WIDTH;
        float marginX = (float) H / (X + 1);
        float marginY = (float) W / (Y + 1);
        float x = 0;
        float y;
        Unit[][] map = dataSet.map;
        //noinspection Duplicates
        for (Unit[] us : map) {
            x += marginX;
            y = 0;
            for (Unit u : us) {
                y += marginY;
                if (u.color == ENEMY)
                    d.setFont(b);
                else d.setFont(s);
                d.drawString(u.toString(), y, x);
            }
        }
    }

    @Deprecated
    @SuppressWarnings("unused")
    public void neatDrawing(Graphics2D d) {
        d.setFont(getFont().deriveFont(1.6f));
        int H = getHeight();
        int W = getWidth();
        int X = dataSet.HEIGHT;
        int Y = dataSet.WIDTH;
        float marginX = (float) H / (X + 1);
        float marginY = (float) W / (Y + 1);
        float margin = Math.min(marginX, marginY);
        float x = margin;
        float y;
        for (int i = 0; i < X; i++, x += margin) {
            y = margin;
            for (int j = 0; j < Y; j++, y += margin) {
                d.drawString(dataSet.map[i][j].toString(), y, x);//swing is opposite from my designation
            }
        }
//        drawEnemy(d);
    }

    @Deprecated
    @SuppressWarnings("unused")
    public void autoSizeDrawing(Graphics2D d) {
        d.setFont(getFont().deriveFont(1.6f));
        int H = getHeight();
        int W = getWidth();
        int X = dataSet.HEIGHT;
        int Y = dataSet.WIDTH;
        float marginX = (float) H / (X + 1);
        float marginY = (float) W / (Y + 1);

        float x = marginX;
        float y;
//        d.setFont(getFont().deriveFont(Font.BOLD));

        for (int i = 0; i < X; i++, x += marginX) {
            y = marginY;
            for (int j = 0; j < Y; j++, y += marginY) {
                d.drawString(dataSet.map[i][j].toString(), y, x);//swing is opposite from my designation
            }
        }
    }

    public MapSet getSet() {
        return dataSet;
    }


    /**
     * This inspection analyzes method control and data flow to report possible conditions that are always true or false, expressions whose value is statically proven to be constant, and situations that can lead to nullability contract violations.
     Variables, method parameters and return values marked as @Nullable or @NotNull are treated as nullable (or not-null, respectively) and used during the analysis to check nullability contracts, e.g. report possible NullPointerException errors.
     More complex contracts can be defined using @Contract annotation, for example:
     @Contract("_, null -> null") — method returns null if its set.second argument is null @Contract("_, null -> null; _, !null -> !null") — method returns null if its set.second argument is null and not-null otherwise @Contract("true -> fail") — a typical assertFalse method which throws an exception if true is passed to it
     */
}
