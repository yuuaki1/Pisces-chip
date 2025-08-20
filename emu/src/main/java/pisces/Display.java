package pisces;

/**
 * Display uses 64 x 32 monochrome panel, each pixel is either 1 or 0
 */

public class Display {
    public static final int COLS = 64;
    public static final int ROWS = 32;

    private final boolean[][] pixels = new boolean[ROWS][COLS];

    // Clear the display
    public void cls() {
        for (int row = 0; row < 32; row++) {
            for (int col = 0; col < 64; col++) {
                pixels[row][col] = false; // set all pixels to off
            }
        }
    }

    public boolean getPixel(int row, int col) {
        col = col % 64;
        row = row % 32; // wrap around if out of bounds
        col = Math.abs(col); // ensure col is non-negative
        row = Math.abs(row); // ensure row is non-negative
        return pixels[row][col];
    }

    public void setPixel(int row, int col, boolean val) {
        col = col % 64;
        row = row % 32; // wrap around if out of bounds
        col = Math.abs(col); // ensure col is non-negative
        row = Math.abs(row); // ensure row is non-negative
        pixels[row][col] = val; // set pixel value
    }
}
