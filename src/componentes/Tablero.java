package componentes;

import characters.Pacman;
import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tablero {

//    private int wordX = 300;
//    private int wordY = 500;
    private int MATRIZ = 5;
    private int pixelSizeTitle = 5;
    private int pixelSizeGameover = 10;

    private BufferedImage buffer;
    private Graphics gBuffer;
    private Graficos g;

    public Tablero(BufferedImage buffer, Graficos graficos, Pacman pacman) {
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
        this.g = graficos;
    }

    public void printPacmanLetter(int[][] letra, int wordX, int wordY) {
        if (buffer != null) {
            for (int i = 0; i < MATRIZ; i++) {
                for (int j = 0; j < MATRIZ; j++) {
                    if (letra[i][j] == 1) {
                        int x = wordX + j * pixelSizeTitle;
                        int y = wordY + i * pixelSizeTitle;
                        g.fillRect(x, y, x + pixelSizeTitle, y + pixelSizeTitle, Color.WHITE);
                    }
                }
            }
        }
    }
    
    public void printGameoverLetter(int[][] letra, int wordX, int wordY) {
        if (buffer != null) {
            for (int i = 0; i < MATRIZ; i++) {
                for (int j = 0; j < MATRIZ; j++) {
                    if (letra[i][j] == 1) {
                        int x = wordX + j * pixelSizeGameover;
                        int y = wordY + i * pixelSizeGameover;
                        g.fillRect(x, y, x + pixelSizeGameover, y + pixelSizeGameover, Color.WHITE);
                    }
                }
            }
        }
    }
    
    public void drawCherry(int x, int y) {
        int cherrySize = 8;
        int stickGrossor = 4;
        //Red circles for cherry
        g.fillCircle(x, y, cherrySize, Color.RED);
        g.fillCircle(x + 10, y + 8, cherrySize, Color.RED);
        g.drawCircle(x + 10, y + 8, cherrySize + 2, Color.BLACK);
        //Cherry sticks
        g.drawThickLine(x, y - 3, x + 20, y - 10, stickGrossor, Color.ORANGE);
        g.drawThickLine(x + 10, y, x + 20, y - 10, stickGrossor, Color.ORANGE);
    }
    
    public void drawVidas(int x, int y) {
        int pacmanSize = 15;
        g.fillCircle(x, y, pacmanSize, Color.yellow);
    }

    public int[][] printP() {
        int p[][] = {
            {1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0}
        };
        return p;
    }

    public int[][] printA() {
        int a[][] = {
            {0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 1, 1, 1, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0}
        };
        return a;
    }

    public int[][] printC() {
        int c[][] = {
            {0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 1, 1, 1, 0}
        };
        return c;
    }

    public int[][] printM() {
        int m[][] = {
            {1, 0, 0, 0, 1},
            {1, 1, 0, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1}
        };
        return m;
    }

    public int[][] printN() {
        int n[][] = {
            {1, 0, 0, 1, 0},
            {1, 1, 0, 1, 0},
            {1, 0, 1, 1, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0}
        };
        return n;
    }

    public int[][] printG() {
        int g[][] = {
            {0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 0, 0, 1, 0},
            {0, 1, 1, 1, 0}
        };
        return g;
    }

    public int[][] printE() {
        int e[][] = {
            {1, 1, 1, 1, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 1, 1, 0}
        };
        return e;
    }

    public int[][] printO() {
        int o[][] = {
            {0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0},
            {0, 1, 1, 0, 0}
        };
        return o;
    }

    public int[][] printV() {
        int v[][] = {
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0}
        };
        return v;
    }
    
    public int[][] printR() {
        int r[][] = {
            {1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0},
            {1, 1, 1, 0, 0},
            {1, 0, 1, 0, 0},
            {1, 0, 1, 1, 0}
        };
        return r;
    }
    
    public int[][] printSadFace() {
        int sadFace[][] = {
            {0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 0, 0, 0, 1}
        };
        return sadFace;
    }
}
