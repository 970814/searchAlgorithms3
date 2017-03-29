package someSearchAlgorithms.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Arc2D;

import static someSearchAlgorithms.test.PaintAnPerson.paintHeroRobot3;

/**
 * Created by computer on 2016/12/8.
 */
public class Test7 {
    public static void main(String[] args) {
        new JFrame() {
            int x = 245;
            int y = 80;

            {
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setSize(800, 600);
                setLocationRelativeTo(null);
            }

            {
                addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();

                        switch (c) {
                            case 'a':
                                x -= 2;
                                break;
                            case 's':
                                y+= 2;
                                break;
                            case 'w':
                                y-= 2;
                                break;
                            case 'd':
                                x+= 2;
                                break;
                            default:
                                return;
                        }
                        repaint();
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });

                setContentPane(new JComponent() {

                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D d = (Graphics2D) g;
                        d.drawString(x + ", " + y, 0, 200);
                        paintHeroRobot3(x, y, d);
//                        paintHeroRobot3(200, 100, d);
//                        for (int i = 0; i <= 180; i += 30) {
//                            d.draw(new Arc2D.Double(200 +  i, 100, 20, 120, i, 90, Arc2D.OPEN));
//                        }
                        d.draw(new Arc2D.Double(0, 0, 16, 120, 30, 90, Arc2D.OPEN));

                        d.draw(new Arc2D.Double(20, 0, 16, 120, 60, 90, Arc2D.OPEN));

                    }
                });
            }
        }.setVisible(true);
    }
}
