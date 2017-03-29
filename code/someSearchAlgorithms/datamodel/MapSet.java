package someSearchAlgorithms.datamodel;



import java.awt.*;
import java.util.Random;

import static someSearchAlgorithms.datamodel.Unit.*;


/**
 * Created by L on 2016/11/20.
 */
public class MapSet {//MapSet
    public static boolean showPath = false;
    public static boolean showLocation = false;
    public static Color[] bgColors = {
            new Color(0xf5, 0xe8, 0xf9),
            new Color(0xfe, 0xf1, 0xbc),
            new Color(0xc6, 0xfb, 0xf4),
            new Color(0xf9, 0xec, 0xe2),
            new Color(238, 238, 238),
            new Color(30, 163, 98),
            new Color(255, 205, 65),
    };

    public int HEIGHT;//row
    public int WIDTH;//column
    double proportion;
    public MapSet() {
        this(270, 450, 0);
    }

    public MapSet(int HEIGHT, int WIDTH, double proportion) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        this.proportion = proportion;
        initialize();
    }

    public Unit[][] map;
    Random random = new Random();

    private void initialize() {
        map = new Unit[HEIGHT][WIDTH];
        {
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    map[i][j] = new Unit(i, j);
                }
            }
        }
    }

    public void nextEnemy() {
        int h = random.nextInt(HEIGHT);
        int w = random.nextInt(WIDTH);
        addEnemy(h, w, random.nextInt(Math.min(HEIGHT, WIDTH) / 8));
    }

    public void addEnemy0(int h, int w, float r) {
        if (map[h][w].color != ENEMY) {
            map[h][w].color = ENEMY;
            map[h][w].r = r;
            setDangerBounds(map[h][w]);
        }
    }


    public void addEnemy(int h, int w, float r) {
        if (map[h][w].color != ENEMY) {
            map[h][w].color = ENEMY;
            map[h][w].r = (float) (r * proportion);
            setDangerBounds(map[h][w], r);
        }
    }

    @SuppressWarnings("Duplicates")
    private void setDangerBounds(Unit unit, final float r) {
//        final double r = unit.r;
        final int x = unit.x;
        final int y = unit.y;
        int k = (int) Math.ceil(r);
        int p = unit.x - k;
        for (int i = 0; i <= k * 2; i++, p++) {
            int q = unit.y - k;
            for (int j = 0; j <= k * 2; j++, q++) {
                if (p < 0 || p >= HEIGHT || q < 0 || q >= WIDTH || (i == 0 || i == k * 2) && (j == 0 || j == k * 2)) {
                    continue;//no check
                }
                if (map[p][q].color != ENEMY) {
                    double d = Point.distance(p, q, x, y);
                    if (d <= r) {
                        map[p][q].color = DANGER;
                    }
                }
            }
        }
        map[x][y].color = ENEMY;
    }


    @SuppressWarnings("Duplicates")
    private void setDangerBounds(Unit unit) {
        setDangerBounds(unit,unit.r);
    }

    public Unit getUnit(int x, int y) {
//        checkBounds(x, y);
        return map[x][y];
    }

    private boolean checkBounds(int x, int y) {
        if (x < 0 || x >= HEIGHT || y < 0 || y >= WIDTH) {
            throw new ArrayIndexOutOfBoundsException(x + ", " + y);
        }
        return true;
    }

    public void toWhite() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (map[i][j].color == BLACK) {
                    map[i][j].color = WHITE;
//                    map[i][j].isBlack = false;
                }
            }
        }
    }
}
