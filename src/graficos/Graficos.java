package graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Graficos extends Canvas {

    private final int WIDTH;
    private final int HEIGHT;
    private BufferedImage buffer;
    private Graphics gBuffer;

    // Variables para la traslación
    private int translateX = 0;
    private int translateY = 0;

    public Graficos(int width, int height, BufferedImage buffer) {
        //setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void fillRect(int x0, int y0, int x1, int y1, Color color) {
        x0 += translateX;
        y0 += translateY;
        x1 += translateX;
        y1 += translateY;

        int nx0 = Math.min(x0, x1);
        int ny0 = Math.min(y0, y1);
        int nx1 = Math.max(x0, x1);
        int ny1 = Math.max(y0, y1);

        for (int y = ny0; y <= ny1; y++) {
            int xInicio = -1;
            for (int x = nx0; x <= nx1; x++) {
                if (xInicio == -1) {
                    xInicio = x;
                }
                if (xInicio != -1) {
                    putPixel(xInicio, y, color);
                    xInicio = -1;
                }
            }
        }
    }

    public void drawRect(int x0, int y0, int x1, int y1, Color color) {
        drawLine(x0, y0, x1, y0, color);
        drawLine(x0, y1, x1, y1, color);
        drawLine(x0, y0, x0, y1, color);
        drawLine(x1, y0, x1, y1, color);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        x1 += translateX;
        y1 += translateY;
        x2 += translateX;
        y2 += translateY;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x1, y1, color);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
        repaint();
    }

    public void fillCircle(int x0, int y0, int RADIO, Color fillColor) {
        for (int y = y0 - RADIO; y <= y0 + RADIO; y++) {
            for (int x = x0 - RADIO; x <= x0 + RADIO; x++) {
                if (Math.pow(x - x0, 2) + Math.pow(y - y0, 2) <= Math.pow(RADIO, 2)) {
                    putPixel(x, y, fillColor);
                }
            }
        }
        repaint();
    }

    public void drawCircle(int x0, int y0, int RADIO, Color color) {
        for (int t = 0; t <= 45; t++) {
            int x = (int) (RADIO * Math.sin(Math.toRadians(t)));
            int y = (int) (RADIO * Math.cos(Math.toRadians(t)));

            putPixel(x0 + x, y0 + y, color);
            putPixel(x0 + y, y0 + x, color);
            putPixel(x0 + y, y0 - x, color);
            putPixel(x0 + x, y0 - y, color);

            putPixel(x0 - x, y0 - y, color);
            putPixel(x0 - y, y0 - x, color);
            putPixel(x0 - y, y0 + x, color);
            putPixel(x0 - x, y0 + y, color);
        }
        repaint();
    }

    public void drawOval(int x0, int y0, int x1, int y1, Color color) {
        int a = Math.abs(x1 - x0) / 2;
        int b = Math.abs(y1 - y0) / 2;
        int xCenter = (x0 + x1) / 2;
        int yCenter = (y0 + y1) / 2;

        int x = 0;
        int y = b;
        int aSquared = a * a;
        int bSquared = b * b;
        int twoASquared = 2 * aSquared;
        int twoBSquared = 2 * bSquared;
        int xChange = bSquared * (1 - 2 * a) + 2 * aSquared * y;
        int yChange = aSquared * (1 + 2 * b) - 2 * bSquared * x;
        int ellipseError = (int) (bSquared - aSquared * b + 0.25 * aSquared);

        while (x * bSquared <= y * aSquared) {
            putPixel(xCenter + x, yCenter + y, color);
            putPixel(xCenter - x, yCenter + y, color);
            putPixel(xCenter + x, yCenter - y, color);
            putPixel(xCenter - x, yCenter - y, color);

            x++;
            if (ellipseError < 0) {
                ellipseError += twoBSquared * x + bSquared;
            } else {
                y--;
                ellipseError += twoBSquared * x - twoASquared * y + bSquared;
            }
        }

        x = a;
        y = 0;
        xChange = aSquared * (1 - 2 * b) + 2 * bSquared * x;
        yChange = bSquared * (1 + 2 * a) - 2 * aSquared * y;
        ellipseError = (int) (aSquared - bSquared * a + 0.25 * bSquared);

        while (x * bSquared > y * aSquared) {
            putPixel(xCenter + x, yCenter + y, color);
            putPixel(xCenter - x, yCenter + y, color);
            putPixel(xCenter + x, yCenter - y, color);
            putPixel(xCenter - x, yCenter - y, color);

            y++;
            if (ellipseError < 0) {
                ellipseError += twoASquared * y + aSquared;
            } else {
                x--;
                ellipseError += twoASquared * y - twoBSquared * x + aSquared;
            }
        }

        repaint();
    }

    public void drawImage(BufferedImage img, int x, int y) {
        Graphics g = buffer.getGraphics();
        g.drawImage(img, x + translateX, y + translateY, null);
    }

    public void drawImage(BufferedImage img, int x, int y, int width, int height) {
        Graphics g = buffer.getGraphics();
        g.drawImage(img, x + translateX, y + translateY, width, height, null);
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void translate(int x, int y) {
        this.translateX = x;
        this.translateY = y;
    }

    public void limpiarBuffer() {
        Graphics g = buffer.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

}
