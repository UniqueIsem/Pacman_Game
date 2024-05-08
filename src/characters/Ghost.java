package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost {

    private int x = 435;
    private int y = 250;
    private final int ghostSize = 13;
    private final int movimiento = 3;
    private boolean redUsed, pinkUsed, orangeUsed, cyanUsed;
    Random random = new Random();
    int direccion = 0;
    
    Laberinto laberinto;
    
    public Ghost(BufferedImage buffer, Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    public void dibujarFantasma(Graficos g, Color c) {
        //BODY AND EYES
        if (true) {
            
        }
        g.fillCircle(x, y, 15, c);
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
                cambioDeDireccion();
            }
        } else if (direccion == 3 && columnaPacman < laberinto.getColumnas() - 1) { // RIGHT
            if (maze[filaPacman][columnaPacman] != 1) { 
                x += movimiento;
            } else {
                cambioDeDireccion();
            }
        }
    }

    public void cambioDeDireccion() {
        // Cambio de direccion del fantasma de manera aleatorea
        direccion = random.nextInt(4);
    }
}
