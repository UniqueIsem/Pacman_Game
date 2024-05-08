package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost {

    private int x = 466;
    private int y = 251;
    private final int ghostSize = 13;
    private final int movimiento = 2;
    private boolean redUsed, pinkUsed, orangeUsed, cyanUsed;
    private Color[] colors = new Color[4];
    Random random = new Random();
    int direccion = 0;

    Laberinto laberinto;

    public Ghost(BufferedImage buffer, Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    public void dibujarFantasma(Graficos g) {
        //BODY AND EYES
        colors[0] = Color.red;
        colors[1] = Color.orange;
        colors[2] = Color.pink;
        colors[3] = Color.cyan;
        if (!redUsed) {
            g.fillCircle(x, y, ghostSize, colors[0]);
        } else if (!orangeUsed) {
            g.fillCircle(x, y, ghostSize, colors[1]);
        } else if (!pinkUsed) {
            g.fillCircle(x, y, ghostSize, colors[2]);
        } else if (!cyanUsed) {
            g.fillCircle(x, y, ghostSize, colors[3]);
        }
        g.drawDottedCircle(x, y, ghostSize + 4, Color.white);
        g.fillCircle(x - 8, y, 5, Color.white);
        g.fillCircle(x - 6, y, 3, Color.black);
        g.fillCircle(x + 8, y, 5, Color.white);
        g.fillCircle(x + 6, y, 3, Color.black);
    }

    public void moverFantasma() {
        // Coordenadas del fantasma según la matriz del laberinto
        int[][] maze = laberinto.getLaberinto();
        int filaPacman = (y + ghostSize + 1 / 2) / laberinto.getCeldaSize();
        int columnaPacman = (x + ghostSize + 1 / 2) / laberinto.getCeldaSize();

        if (direccion == 0 && filaPacman > 0) { // UP
            if (maze[filaPacman - 1][columnaPacman] != 1) { //Entra si no detecta colision
                y -= movimiento;
            } else { //Cambia de direccion si detecta colision
                cambioDeDireccion();
            }
        } else if (direccion == 1 && columnaPacman > 0) { // LEFT
            if (maze[filaPacman][columnaPacman - 1] != 1) {
                x -= movimiento;
            } else {
                cambioDeDireccion();
            }
        } else if (direccion == 2 && filaPacman < laberinto.getFilas() - 1) { // DOWN
            if (maze[filaPacman][columnaPacman] != 1) {
                y += movimiento;
            } else {
                y--;
                cambioDeDireccion();
            }
        } else if (direccion == 3 && columnaPacman < laberinto.getColumnas() - 1) { // RIGHT
            if (maze[filaPacman][columnaPacman] != 1) {
                x += movimiento;
            } else {
                x--;
                cambioDeDireccion();
            }
        }

        //Verifica si llego al borde para aparecer del otro lado
        if ((x > 30 && x < 33) && (y >= 240 && y <= 260)) {
            if (maze[filaPacman][columnaPacman - 1] != 1) {
                x = 879;
                x -= movimiento;
            }
        } else if ((x > 880 && x < 888) && (y >= 240 && y <= 260)) {
            if (maze[filaPacman][columnaPacman] != 1) {
                x = 34;
                x += movimiento;
            }            
        }
        
        //System.out.println("GHOST X: " + x + " Y: " + y);
    }

    public void cambioDeDireccion() {
        // Cambio de direccion del fantasma de manera aleatorea
        direccion = random.nextInt(4);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
