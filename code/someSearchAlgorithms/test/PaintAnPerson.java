package someSearchAlgorithms.test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.sqrt;
import static someSearchAlgorithms.datamodel.MapSet.bgColors;

/**
 * Created by computer on 2016/11/28.
 */
public class PaintAnPerson {
    static String yourName = getName("-我-");

    private static String getName(String s) {
        char[] name = new char[s.length()];
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != ' ') {
                name[k++] = Character.toLowerCase(s.charAt(i));
            }
        }
        return new String(name, 0, k);
    }

    final static BufferedImage robot;
    final static BufferedImage robot2;
    final static BufferedImage robot3;
    final static BufferedImage you;
    public static int w;
    public static int h;
    public static int w2;
    public static int h2;
    public static int w3;
    public static int h3;
    static {
        Graphics2D d;


        w = 16;
        h = 46;
        robot2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        d = (Graphics2D) robot2.getGraphics();
        paintHeroRobot2(d, w, h);

        w = 16;
        h = 46;
        robot = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        d = (Graphics2D) robot.getGraphics();
        paintHeroRobot(d, w, h);

        w = 16;
        h = 46;
        w2 = yourName.length() * 8;
        h2 = 66;
        you = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_RGB);
        d = (Graphics2D) you.getGraphics();
        paintHeroU(d, w2, h2);

        w3 = 40;
        h3 = 80;
        robot3 = new BufferedImage(w3, h3, BufferedImage.TYPE_INT_RGB);
        d = (Graphics2D) robot3.getGraphics();
        paintHeroRobot3(d, w3, h3);


    }

    private static void paintHeroRobot3(Graphics2D d, int width, int height) {
        d.setColor(bgColors[3]);
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, width, height);
        d.fill(rectangle2D);
        paintHeroU(10, 30, d);
        d.setColor(Color.BLACK);
        d.draw(new Arc2D.Double(0, 0, 16, 120, 30, 90, Arc2D.OPEN));

        d.draw(new Arc2D.Double(20, 0, 16, 120, 60, 90, Arc2D.OPEN));

    }

    private static void paintHeroU(Graphics2D d, double width, double height) {
//        d.setColor(new Color(238, 238, 238));
        d.setColor(bgColors[3]);
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, width, height);
        d.fill(rectangle2D);
        d.setColor(bgColors[5]);

        int k = h2 / 5;
        d.setFont(new Font("", Font.ITALIC, k));
        d.drawString(yourName, 0, k);

        paintHeroRobot2((int) ((w2 - w) / 2.), (int) (k * 1.5), d);

    }

    public static void paintHeroRobot2(int x, int y, Graphics2D d) {
        d.drawImage(robot2, x, y, null);
    }
    public static void paintHeroRobot2(float x, float y, Graphics2D d) {
        d.drawImage(robot2, ((int) (x - (w / 2.))), ((int) (y - (h / 2.))), null);
    }

    public static void paintHeroRobot(int x, int y, Graphics2D d) {
        d.drawImage(robot, x, y, null);
    }
    public static void paintHeroRobot(float x, float y, Graphics2D d) {
        d.drawImage(robot, ((int) (x - (w / 2.))), ((int) (y - (h / 2.))), null);
    }
    public static void paintHeroU(int x, int y, Graphics2D d) {
        d.drawImage(you, x - (int) ((w2 - w) / 2.), y - (int) (h2 / 5 * 1.5), null);
//        d.drawImage(you, x - (int) (w2 / 2.), y - (int) (h2 / 2.), null);
    }
    public static void paintHeroRobot3(int x, int y, Graphics2D d) {
        d.drawImage(robot3, x - (int) (w3 / 2.), y - (int) (h3 / 2.), null);
    }

    @SuppressWarnings("Duplicates")
    public static void paintHeroRobot2(Graphics2D d, double width, double height) {
        d.setColor(bgColors[3]);
//        Color color = d.getColor();
//        System.out.println(color);
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, width, height);
        d.fill(rectangle2D);
        d.setColor(Color.BLACK);
        double p = width / 3.0;
        double q = height/3.5;

//        d.setFont(new Font("", Font.PLAIN, 8));
//        d.drawString("You", 0, 5);
        double k = 0;

        Ellipse2D ellipse = new Ellipse2D.Double();
        //head
        ellipse.setFrame(p / 2, k, p * 2, q);


        d.draw(ellipse);
        double h = q + k;
        float r = 2f;
        double x = p - r / 2;
        double y = h / 2.8;


        d.fill(new Ellipse2D.Double(x - r / 2, y - r / 2 , r, r));
        x = p * 2 - r ;
        d.fill(new Ellipse2D.Double(x - r / 2, y - r / 2  , r, r));




        double bottomX = width / 2;
        double bottomY = h;


        //neck
        d.drawLine(((int) bottomX), ((int) bottomY), ((int) bottomX), (int) (bottomY += h / 3));//neck

        {//hand
            double handSize = h * 0.5;
            double x0 = bottomX - handSize / sqrt(2);
            double y0 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), ((int) x0), (int) (y0));
            double x1 = bottomX + handSize / sqrt(2);
            double y1 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), ((int) x1), (int) (y1));
        }
        //body
        d.drawLine(((int) bottomX), ((int) bottomY), ((int) bottomX), (int) (bottomY += h * 1.1));//body


        {//foot
            double footSize = h;
            double x0 = bottomX - footSize / sqrt(3);
            double y0 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), (int) x0, (int) y0);
            double x1 = bottomX + footSize / sqrt(3);
            double y1 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), (int) x1, (int) y1);
        }
    }

    @SuppressWarnings("Duplicates")
    public static void paintHeroRobot(Graphics2D d, double width, double height) {
        d.setColor(bgColors[3]);
//        Color color = d.getColor();
//        System.out.println(color);
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, width, height);
        d.fill(rectangle2D);
        d.setColor(Color.BLACK);
        double p = width / 3.0;
        double q = height/3.5;


//        Ellipse2D ellipse = new Ellipse2D.Double();
//        //head
//        ellipse.setFrame(p / 2, 0, p * 2, q);
//
//        d.draw(ellipse);
        d.drawRect((int) (p / 2), 0, ((int) (p * 2)), (int) q);

        double h = q;

        double bottomX = width / 2;
        double bottomY = h;


        //neck
        d.drawLine(((int) bottomX), ((int) bottomY), ((int) bottomX), (int) (bottomY += h / 3));//neck

        {//hand
            double handSize = h * 0.5;
            double x0 = bottomX - handSize / sqrt(2);
            double y0 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), ((int) x0), (int) (y0));
            double x1 = bottomX + handSize / sqrt(2);
            double y1 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), ((int) x1), (int) (y1));
        }
        //body
        d.drawLine(((int) bottomX), ((int) bottomY), ((int) bottomX), (int) (bottomY += h * 1.1));//body


        {//foot
            double footSize = h;
            double x0 = bottomX - footSize / sqrt(3);
            double y0 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), (int) x0, (int) y0);
            double x1 = bottomX + footSize / sqrt(3);
            double y1 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), (int) x1, (int) y1);
        }
    }


    @SuppressWarnings("Duplicates")
    public static void paintHero(Graphics2D d, double width, double height) {

        Ellipse2D ellipse = new Ellipse2D.Double();
        double x = width;
        double y = height;
        double w = 7.0;
        double h = 10.0;

        ellipse.setFrame(width, height, w, h);
        d.draw(ellipse);
        double bottomX = x + w / 2;
        double bottomY = y + h;


        //neck
        d.drawLine(((int) bottomX), ((int) bottomY), ((int) bottomX), (int) (bottomY += h / 3));//neck

        {//hand
            double handSize = h * 0.7;
            double x0 = bottomX - handSize / sqrt(2);
            double y0 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), ((int) x0), (int) (y0));
            double x1 = bottomX + handSize / sqrt(2);
            double y1 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), ((int) x1), (int) (y1));
        }
        //body
        d.drawLine(((int) bottomX), ((int) bottomY), ((int) bottomX), (int) (bottomY += h * 1.1));//body


        {//foot
            double footSize = h;
            double x0 = bottomX - footSize / sqrt(2);
            double y0 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), (int) x0, (int) y0);
            double x1 = bottomX + footSize / sqrt(2);
            double y1 = h + bottomY;
            d.drawLine(((int) bottomX), ((int) bottomY), (int) x1, (int) y1);
        }
    }
    public static void paintHero(Graphics2D d, int width, int height) {
        paintHero(d, (double) width / 3, (double) height / 3);
    }
    public static void main(String[] args) {
        int w = 15;
        int h = 45;
        BufferedImage hero = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D d = (Graphics2D) hero.getGraphics();
        {
            paintHero(d, w, h);
        }
        EventQueue.invokeLater(() ->
                new JFrame() {
                    int Height = 800;
                    int Width = 600;

                    {
                        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        setSize(Height, Width);
                        setLocationRelativeTo(null);
                    }

                    {
                        setContentPane(new JComponent() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                Graphics2D d = (Graphics2D) g;
//                                paintHero(d, Width, Height);
                                d.drawImage(hero, 0, 0, null);
                            }
                        });
                    }
                }.setVisible(true)
        );

    }
}
