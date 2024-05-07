package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost {

    private int x = 430;
    private int y = 250;
    private final int ghostSize = 30;
    private final int movimiento = 10;
    Random random = new Random();
    int direccion;
    Color color;

    private BufferedImage buffer;
    private Graphics gBuffer;

    public Ghost(BufferedImage buffer) {
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
    }

    public void moverFantasma(Laberinto laberinto) {
        // Coordenadas del fantasma según la matriz del laberinto
        int[][] maze = laberinto.getLaberinto();
        int filaPacman = (y + ghostSize + 1 / 2) / laberinto.getCeldaSize();
        int columnaPacman = (x + ghostSize + 1 / 2) / laberinto.getCeldaSize();

        if (direccion == 0 && filaPacman > 0) { // UP
            if (maze[filaPacman - 1][columnaPacman] != 1) { // Verifica la colisión
                y -= movimiento;
                //System.out.println("X: " + x + " Y: " + y + " UP");
            }
        } else if (direccion == 1 && columnaPacman > 0) { // LEFT
            if (maze[filaPacman][columnaPacman - 1] != 1) { // Verifica la colisión
                x -= movimiento;
                //System.out.println("X: " + x + " Y: " + y + " LEFT");
            }
        } else if (direccion == 2 && filaPacman < laberinto.getFilas() - 1) { // DOWN
            if (maze[filaPacman][columnaPacman] != 1) { // Verifica la colisión
                y += movimiento;
                //System.out.println("X: " + x + " Y: " + y + "DOWN");
            }
        } else if (direccion == 3 && columnaPacman < laberinto.getColumnas() - 1) { // RIGHT
            if (maze[filaPacman][columnaPacman] != 1) { // Verifica la colisión
                x += movimiento;
                //System.out.println("X: " + x + " Y: " + y + " RIGHT");
            }
        }
    }
    
    public void cambioDeDireccion() {
        // Cambio de direccion del fantasma de manera aleatorea
        direccion = random.nextInt(4);
        //System.out.println("MESSAGE: ghost change direction: " + direccion);
    }

    public void dibujarFantasma(Graficos g, Color c) {
        //BODY AND EYES
        g.limpiarBuffer();
        //g.fillRect(x - 15, y - 12, x + 15, y + 12, c);
        g.fillCircle(x, y, 15, c);
        g.fillCircle(x - 8, y, 5, Color.white);
        g.fillCircle(x - 6, y, 3, Color.black);
        g.fillCircle(x + 8, y, 5, Color.white);
        g.fillCircle(x + 6, y, 3, Color.black);

    }
}
