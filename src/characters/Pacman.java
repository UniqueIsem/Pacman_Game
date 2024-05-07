package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Pacman {

    private int x = 435;
    private int y = 315;
    public boolean[] teclaPresionada = new boolean[4];
    private int pacmanSize = 13;
    private int vidas = 3;
    private final int movimiento = 3;

    Laberinto laberinto;

    //coordenadas para teletransportacion
    //X: -55 Y: 255 LEFT
    //X: 905 Y: 255 RIGHT
    public Pacman(BufferedImage buffer, Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    public void dibujarPacman(Graficos g) {
        //g.limpiarBuffer();
        g.fillCircle(x, y, pacmanSize, Color.yellow);
    }

    public void moverPacman() {
        int[][] maze = laberinto.getLaberinto();
        int filaPacman = (y + pacmanSize + 1 / 2) / laberinto.getCeldaSize();
        int columnaPacman = (x + pacmanSize + 1 / 2) / laberinto.getCeldaSize();

        if (teclaPresionada[0] && filaPacman > 0) { // UP
            if (maze[filaPacman - 1][columnaPacman] != 1) { // Verifica la colisión
                y -= movimiento;
                System.out.println("X: " + x + " Y: " + y + " UP ");
            }
        } else if (teclaPresionada[1] && columnaPacman > 0) { // LEFT
            if (maze[filaPacman][columnaPacman - 1] != 1) { // Verifica la colisión
                x -= movimiento;
                System.out.println("X: " + x + " Y: " + y + " LEFT");
            }
        } else if (teclaPresionada[2] && filaPacman < laberinto.getFilas() - 1) { // DOWN
            if (maze[filaPacman][columnaPacman] != 1) { // Verifica la colisión
                y += movimiento;
                System.out.println("X: " + x + " Y: " + y + " DOWN");
            }
        } else if (teclaPresionada[3] && columnaPacman < laberinto.getColumnas() - 1) { // RIGHT
            if (maze[filaPacman][columnaPacman] != 1) { // Verifica la colisión
                x += movimiento;
                System.out.println("X: " + x + " Y: " + y + " RIGHT");
            }
        }

        if (x == 15) {
            if (maze[filaPacman][columnaPacman - 1] != 1) {
                x -= movimiento;
                System.out.println("TELETRANSPORTACION IZQUIERDA!!!");
            }
        } else if (x == 828 && y == 255) {
            if (maze[filaPacman][columnaPacman] != 1) {
                x += movimiento;
                System.out.println("TELETRANSPORTACION DERECHA!!!");
            }
        }

        //Verifica si está sobre un punto para comerlo
        if (maze[filaPacman][columnaPacman] == 0 || maze[filaPacman][columnaPacman] == 2) {
            laberinto.comerPunto(filaPacman, columnaPacman);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
