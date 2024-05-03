package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pacman {

    private int x = 435;
    private int y = 315;
    public boolean[] teclaPresionada = new boolean[4];
    private int pacmanSize = 15;
    private int vidas = 3;
    private final int movimiento = 3;

    private BufferedImage buffer;
    private Graphics gBuffer;
    Laberinto laberinto;

    //coordenadas para teletransportacion
    //X: -55 Y: 255 LEFT
    //X: 905 Y: 255 RIGHT
    public Pacman(BufferedImage buffer, Laberinto laberinto) {
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
        this.laberinto = laberinto;
    }

    public void dibujarPacman(Graficos g) {
        g.limpiarBuffer();
        g.fillCircle(x, y, pacmanSize, Color.yellow);
        g.drawCircle(x, y, pacmanSize, Color.black);
    }

    public void moverPacman() {
        int[][] maze = laberinto.getLaberinto();
        int filaPacman = y / laberinto.getAltoCelda(); // Calcula la fila del Pacman
        int columnaPacman = x / laberinto.getAnchoCelda();

        if (teclaPresionada[0] && filaPacman > 0) { // UP
            if (maze[filaPacman - 1][columnaPacman] != 1) { // Verifica la colisión
                y -= movimiento;
            }
            System.out.println("X: " + x + " Y: " + y + " UP ");
        }
        if (teclaPresionada[1] && columnaPacman > 0) { // LEFT
            if (maze[filaPacman][columnaPacman - 1] != 1) { // Verifica la colisión
                x -= movimiento;
            }
            System.out.println("X: " + x + " Y: " + y + " LEFT");
        }
        if (teclaPresionada[2] && filaPacman < laberinto.getFilas() - 1) { // DOWN
            if (maze[filaPacman + 1][columnaPacman] != 1) { // Verifica la colisión
                y += movimiento;
            }
            System.out.println("X: " + x + " Y: " + y + " DOWN");
        }
        if (teclaPresionada[3] && columnaPacman < laberinto.getColumnas() - 1) { // RIGHT
            if (maze[filaPacman][columnaPacman + 1] != 1) { // Verifica la colisión
                x += movimiento;
            }
            System.out.println("X: " + x + " Y: " + y + " RIGHT");
        }
    }
}
