package componentes;

import characters.Pacman;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tablero {

    private int vidas;
    Pacman pacman;

    private BufferedImage buffer;
    private Graphics gBuffer;

    public Tablero(BufferedImage buffer) {
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
    }

    public int[][] getP() {
        int p[][] = {
            {1, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0}
        };
        return p;
    }

    public int[][] getA() {
        int a[][] = {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1}
        };
        return a;
    }

    public int[][] getC() {
        int c[][] = {
            {0, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {0, 1, 1, 1}
        };
        return c;
    }

    public int[][] getM() {
        int m[][] = {
            {1, 0, 0, 0, 1},
            {1, 1, 0, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1}
        };
        return m;
    }

    public int[][] getN() {
        int n[][] = {
            {1, 0, 0, 1},
            {1, 1, 0, 1},
            {1, 0, 1, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1}
        };
        return n;
    }

    public int[][] getG() {
        int g[][] = {
            {1, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0}
        };
        return g;
    }

    public int[][] getE() {
        int e[][] = {
            {1, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0}
        };
        return e;
    }

    public int[][] getO() {
        int o[][] = {
            {1, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0}
        };
        return o;
    }

    public int[][] getV() {
        int v[][] = {
            {1, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0}
        };
        return v;
    }
}
