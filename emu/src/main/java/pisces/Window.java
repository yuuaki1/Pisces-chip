package pisces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Window extends JPanel implements PropertyChangeListener {
    // Dynamic width, height of the window in pixels. USER can resize the window
    private int width = 600;
    private int height = 600;
    private final String title = "Pisces-8 emulator";

    private Display display;

    public Window (Display display, Input input) {
        this.display = display;

        JFrame jframe = new JFrame(title);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(this);
        this.setBackground(Color.BLACK);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addKeyListener(input);
        jframe.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                width = getWidth();
                height = getHeight();
            }

            @Override
            public void componentMoved (ComponentEvent e) {

            }

            @Override
            public void componentShown (ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        int col_width = width / Display.COLS;
        int row_height = height / Display.ROWS;

        for (int row = 0; row < Display.ROWS; row++) {
            for (int col = 0; col < Display.COLS; col++) {
                boolean pixel = display.getPixel(row, col);
                if (!pixel) continue;

                int x = col * col_width;
                int y = row * row_height;
                g.fillRect(x, y, col_width, row_height);
            }
        }
    }

    // private void drawGridLines(Graphics g) {
    //     g.setColor(Color.RED);
    //     int col_width = width / Display.COLS;
    //     int row_height = height / Display.ROWS;

    //     for (int r = 0; r < Display.ROWS; r++) {
    //         g.drawLine(0, r * row_height, Display.COLS * col_width, r * row_height);
    //         for (int c = 0; c < Display.COLS; c++) {
    //             g.drawLine(c * col_width, 0, c * col_width, Display.ROWS * row_height);
    //         }
    //     }

    //     // Draw last lines (to close the grid)
    //     g.drawLine(0, Display.ROWS * row_height, Display.COLS * col_width, Display.ROWS * row_height);
    //     g.drawLine(Display.COLS * col_width, 0, Display.COLS * col_width, Display.ROWS * row_height);
    // }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        
    }
}
