package componentes;

import characters.Pacman;
import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Laberinto {

    private final int width = 915, height = 546;
    private final int FILAS = 17;
    private final int COLUMNAS = 29;
    private final int celdaSize = 30;
    public int[][] laberinto;

    private BufferedImage buffer;
    private Graphics gBuffer;

    public Laberinto(BufferedImage buffer) {
        this.laberinto = getLaberinto();
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
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

    public void drawPoints(Graficos g, Pacman pacman) {
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
    
    public void comerPunto(int fila, int columna){
        if (laberinto[fila][columna] == 0 || laberinto[fila][columna] == 2) {
            laberinto[fila][columna] = 3;
        }
    }
    
    

    public int[][] getLaberinto() {
        int laberinto[][]
                = {
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                    {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                    {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 2, 0, 1, 0, 1, 1, 3, 3, 3, 1, 1, 0, 1, 0, 2, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 3, 3, 3, 3, 3, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                    {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
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
