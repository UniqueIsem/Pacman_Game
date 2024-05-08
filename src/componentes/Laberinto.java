package componentes;

import characters.Pacman;
import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Laberinto {

    private final int width = 915, height = 546;
    private final int FILAS = 17;
    private final int COLUMNAS = 31; //
    private final int celdaSize = 30;
    public int[][] laberinto;

    private BufferedImage buffer;
    private Graphics gBuffer;

    public Laberinto(BufferedImage buffer) {
        this.laberinto = getLaberinto();
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
    }

    public void drawBackground(Graficos g) {
        g.fillRect(0, 0, width, height, Color.BLACK);
    }

    public void drawMaze(Graficos g) {
        if (buffer != null) {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    if (laberinto[i][j] == 1) {
                        int x = j * celdaSize;
                        int y = i * celdaSize;
                        g.fillRect(x, y, x + celdaSize, y + celdaSize, Color.BLUE);
                        g.drawRect(x, y, x + celdaSize, y + celdaSize, Color.BLACK);
                    }
                }
            }
        }
    }

    public void drawPills(Graficos g) {
        if (buffer != null) {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    if (laberinto[i][j] == 0) {
                        int x = j * celdaSize + celdaSize / 2;
                        int y = i * celdaSize + celdaSize / 2;
                        int RADIO = celdaSize / 20;
                        g.fillCircle(x, y, RADIO, Color.white);
                    } else if (laberinto[i][j] == 2) {
                        int x = j * celdaSize + celdaSize / 2;
                        int y = i * celdaSize + celdaSize / 2;
                        int RADIO = celdaSize / 10;
                        g.fillCircle(x, y, RADIO, Color.white);
                    }
                }
            }
        }
    }

    public void drawNewPills(Graficos g) {
        if (buffer != null) {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    if (laberinto[i][j] == 3) {
                        laberinto[i][j] = 0;
                        int x = j * celdaSize + celdaSize / 2;
                        int y = i * celdaSize + celdaSize / 2;
                        int RADIO = celdaSize / 20;
                        g.fillCircle(x, y, RADIO, Color.white);
                    } else if (laberinto[i][j] == 4) {
                        laberinto[i][j] = 2;
                        int x = j * celdaSize + celdaSize / 2;
                        int y = i * celdaSize + celdaSize / 2;
                        int RADIO = celdaSize / 10;
                        g.fillCircle(x, y, RADIO, Color.white);
                    }
                }
            }
        }
    }

    public void eatPill(int fila, int columna) {
        if (laberinto[fila][columna] == 0) { //normal pill
            laberinto[fila][columna] = 3;
        }else if (laberinto[fila][columna] == 2) { //super pill
            laberinto[fila][columna] = 4;
        }
    }

    public int[][] getLaberinto() {
        int laberinto[][]
                = {
                    {5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
                    {5, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 5},
                    {5, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 5},
                    {5, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 5},
                    {5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5},
                    {5, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 5},
                    {5, 1, 0, 0, 0, 0, 0, 1, 2, 0, 1, 0, 1, 1, 1, 5, 1, 1, 1, 0, 1, 0, 2, 1, 0, 0, 0, 0, 0, 1, 5},
                    {5, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 5, 1, 5, 1, 5, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5, 1, 5, 1, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 5},
                    {5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5},
                    {5, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 5},
                    {5, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 5},
                    {5, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 5},
                    {5, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 5},
                    {5, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 5},
                    {5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5}
                };
        return laberinto;
    }

    public boolean hayColision(int fila, int columna) {
        return laberinto[fila][columna] == 1;
    }

    public int getAncho() {
        return COLUMNAS * celdaSize;
    }

    public int getAlto() {
        return FILAS * celdaSize;
    }

    public int getFilas() {
        return FILAS;
    }

    public int getColumnas() {
        return COLUMNAS;
    }

    public int getCeldaSize() {
        return celdaSize;
    }
}
