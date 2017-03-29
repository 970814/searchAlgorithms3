package someSearchAlgorithms.test;

import someSearchAlgorithms.graphics.Window;

import java.awt.*;

/**
 * Created by computer on 2016/11/21.
 */
public class Test {




    /*
                                                                                            [1280, 1024]
                                                                                                : 2	: 4	: 8
                                                                                            [1280, 960]
                                                                                                : 2	: 4	: 5	: 8
                                                                                            [1280, 800]
                                                                                                : 2	: 4	: 5	: 8
                                                                                            [1280, 768]
                                                                                                : 2	: 4	: 8
                                                                                            [1280, 720]
                                                                                                : 2	: 4	: 5	: 8
                                                                                            [1280, 600]
                                                                                                : 2	: 4	: 5	: 8
                                                                                            [1152, 864]
                                                                                                : 2	: 3	: 4	: 6	: 8	: 9
                                                                                            [1024, 768]
                                                                                                : 2	: 4	: 8
                                                                                            [800, 600]
                                                                                                : 2	: 4	: 5	: 8*/
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> window = new Window(1280, 768, 4) {{
            setVisible(true);
        }});
    }

    public static Window window;

}

