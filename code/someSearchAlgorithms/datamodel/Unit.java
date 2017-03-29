package someSearchAlgorithms.datamodel;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


import static someSearchAlgorithms.test.PaintAnPerson.*;
import static someSearchAlgorithms.datamodel.MapSet.showLocation;
import static someSearchAlgorithms.datamodel.MapSet.showPath;

/**
 * Created by L on 2016/11/20.
 */
public class Unit {//MapUnit
    public int color;
    public int direction;
    public Unit pi;
    public int distance = INFINITY;
    public int x;//h
    public int y;//w

    float r;
    public boolean isLocation = false;
    public boolean isPath = false;//一个点如果它是路径，那么它的颜色一定是白色的
    public boolean isTest;
//    public boolean isBlack = false;

    public boolean isSafe() {
//        return color == WHITE || color == BLACK;
        return color == WHITE;
    }
    public Unit(int x, int y) {
        this(WHITE, x, y);
    }

    public Unit(int color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        switch (color) {
            case WHITE:
                return "•";//.
            case GRAY:
                switch (direction) {
                    case Up:
                        return UP;
                    case Down:
                        return DOWN;
                    case Left:
                        return LEFT;
                    case Right:
                        return RIGHT;
                    case DownLeft:
                        return DOWN_LEFT;
                    case DownRight:
                        return DOWN_RIGHT;
                    case UpLeft:
                        return UP_LEFT;
                    case UpRight:
                        return UP_RIGHT;
                    default:
                        return "-";
                }
            case DANGER:
                return " ";
            case ENEMY:
                return "U";
            default:
                throw new UnknownError();
        }
    }

//    Color bgColor = new Color(255, 246, 245);
//    Rectangle2D enemy = new Rectangle2D.Float(x, y, 8f, 8f);
//    Rectangle2D danger = new Rectangle2D.Float(x, y, 0.5f, 0.5f);


    /**
     * 这些矩形和圆圈，下次要被改写成静态变量，只保存一份，
     * 大大提高效率，否则每一帧都需要创建O(n^2)个对象，一直在创建！
     */

    public void toImage(Graphics2D d, float x, float y) {
        if (showLocation && isLocation) {
            d.setFont(d.getFont().deriveFont(10.f));
            d.setColor(Color.BLACK);
            d.drawString(((int) x) + ", " + ((int) y), x, y);
            d.setColor(Color.RED);
            d.fill(new Rectangle2D.Float(x, y, 2.5f, 2.5f));
        }

        float r;
        switch (color) {
            case WHITE:
               /* d.setColor(Color.BLACK);
                r = 5f;
                d.draw(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));*/
                break;
            case ENEMY:
                d.setColor(Color.RED);
                r = 5f;
                d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
                d.draw(new Ellipse2D.Float(x - this.r, y - this.r, 2 * this.r, 2 * this.r));
                break;
            case DANGER:
//                d.setColor(Color.BLUE.darker());
//                d.draw(new Rectangle2D.Float(x, y, 0.5f, 0.5f));
                break;
            case GRAY2:
                d.setColor(Color.RED);
                r = 8f;
                d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
                paintHeroRobot3(((int) x), ((int) y), d);
//                paintHeroRobot(x, y, d);
//                paintHeroRobot2(x, y, d);
//                r = 5f;//test
//                d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
                break;
            case GRAY:
                d.setColor(Color.RED);
//                r = 5f;
//                d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
                paintHeroRobot2(x, y, d);
//                paintHeroRobot(((int) x), ((int) y), d);
                break;
            case BLACK:
                r = 5f;
//                r = 5f;
//                d.setColor(bgColors[0]);
                d.setColor(Color.RED);
                d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));

            default:
                break;
        }
//        if (isTest) {//test
//            d.setColor(Color.BLUE);
//            r = 5f;
//            d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
//        }
        if (showPath && isPath) {
            d.setColor(Color.BLUE);
            r = 2f;
            d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
        }
//        if (isBlack) {
//            d.setColor(Color.ORANGE);
//            r = 8f;
//            d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
//        }
    }

    public boolean withinThis(int p, int q) {

        int w = (int) (w3 / r);
        int h = ((int) (h3 / r));
        return x - h <= p && p <= x + h && y - w <= q && q <= y + w;
    }

    //test path
  /*  public void toImage(Graphics2D d, float x, float y) {
        if (showLocation && isLocation) {
            d.setFont(d.getFont().deriveFont(10.f));
            d.setColor(Color.BLACK);
            d.drawString(((int) x) + ", " + ((int) y), x, y);
            d.setColor(Color.RED);
            d.fill(new Rectangle2D.Float(x, y, 2.5f, 2.5f));
        }
        if (showPath && isPath) {
            d.setColor(Color.BLUE.brighter().brighter());
            float r = 5f;
            d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));

        }
        float r;
        switch (color) {
            case WHITE:
                d.setColor(Color.BLACK);
                r = 5f;
                d.draw(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
                break;
            case ENEMY:
                d.setColor(Color.RED);

//                d.draw(new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r));
                break;
            case DANGER:
//                d.setColor(Color.BLUE.darker());
//                d.draw(new Rectangle2D.Float(x, y, 0.5f, 0.5f));
                break;
            case GRAY:
                d.setColor(Color.RED);
                r = 5f;
                d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
//                paintHero(((int) x), ((int) y), d);
                break;
            case BLACK:
                r = 1f;
//                d.setColor(bgColors[0]);
                d.setColor(Color.BLACK);
                d.fill(new Rectangle2D.Float(x - r / 2, y - r / 2, r, r));
            default:
                break;
        }

    }*/

    public int relativeTo(Unit k) {
        return locate(this, k);
    }

    @SuppressWarnings("Duplicates")
    public int locate(Unit u, Unit k) {
        int i = u.x;
        int j = u.y;
        int x = k.x;
        int y = k.y;
        int p = i - x;
        int q = j - y;

        if (p > 0) {
            return location0(q, DownRight, DownLeft, Down);
        } else if (p < 0) {
            return location0(q, UpRight, UpLeft, Up);
        } else {
            return location0(q, Right, Left);//if p and q are all zero, than will throw ArrayOutOfBoundsException
        }
    }

    @SuppressWarnings("Duplicates")
    private int location0(int q, char... location) {
        if (q > 0)
            return location[0];
        else if (q < 0)
            return location[1];
        else return location[2];
    }

    public final static int INFINITY = Integer.MIN_VALUE;

    public final static int WHITE = 0;
    public final static int BLACK = 1;
    public final static int GRAY = 2;
    public final static int DANGER = 3;
    public final static int ENEMY = 4;
    public final static int GRAY2 = 5;


    public final static char Up = '↑';
    public final static char Down = '↓';
    public final static char Left = '←';
    public final static char Right = '→';

    public final static String UP = "↑";
    public final static String DOWN = "↓";
    public final static String LEFT = "←";
    public final static String RIGHT = "→";

    public final static char UpLeft = '↖';
    public final static char DownLeft = '↙';
    public final static char DownRight = '↘';
    public final static char UpRight = '↗';

    public final static String UP_LEFT = "↖";
    public final static String DOWN_LEFT = "↙";
    public final static String DOWN_RIGHT = "↘";
    public final static String UP_RIGHT = "↗";




    /**
     * Arrow Set
     * ← ↑ → ↓ ↙ ↘ ↖ ↗ ↰ ↱ ↲ ↳ ↴ ↵ ↶ ↺ ↻ ↷ ➝ ⇄ ⇅ ⇆ ⇇
     * ⇈ ⇉ ⇊ ⇋ ⇌ ⇍ ⇎ ⇏ ⇐ ⇑ ⇒ ⇓ ⇔ ⇕ ⇖ ⇗ ⇘ ⇙ ⇚ ⇛ ↯ ↹ ↔
     * ↕ ⇦ ⇧ ⇨ ⇩ ➫ ➬ ➩ ➪ ➭ ➮ ➯ ➱ ⏎ ➜ ➡ ➥ ➦ ➧ ➨ ➷ ➸ ➻
     * ➼ ➽ ➸ ➹ ➳ ➤ ➟ ➲ ➢ ➣ ➞ ⇪ ➚ ➘ ➙ ➛ ➺ ⇞ ⇟ ⇠ ⇡ ⇢ ⇣
     * ⇤ ⇥ ↜ ↝ ♐ ➴ ➵ ➶ ↼ ↽ ↾ ↿ ⇀ ⇁ ⇂ ⇃ ↞ ↟ ↠ ↡ ↢ ↣ ↤
     * ↪ ↫ ↬ ↭ ↮ ↯ ↩ ⇜ ⇝ ↸ ↚ ↛ ↥ ↦ ↧ ↨
     */ 
}
