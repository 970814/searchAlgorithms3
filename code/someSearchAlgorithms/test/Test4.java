package someSearchAlgorithms.test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by computer on 2016/12/5.
 */
public class Test4 {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new JFrame(){
            {
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setSize(500, 500);
                setLocationRelativeTo(null);

                setContentPane(new JComponent() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D d = (Graphics2D) g;
                        int x = 100;
                        int y = 100;
                        d.drawString("." + x + ", " + y, ((float) x), ((float) y));
                        Rectangle2D rectangle2D = new Rectangle2D.Double(x - x / 2, y - y / 2, x, y);
                        d.draw(rectangle2D);
                    }
                });
            }
        }.setVisible(true));
    }
}
