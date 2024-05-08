package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Pacman {

    private int x = 465;
    private int y = 315;
    private int pacmanSize = 13;
    private final int movimiento = 1;
    public boolean[] teclaPresionada = new boolean[4];
    private int vidas = 3;

    Laberinto laberinto;
    Ghost ghost;

    public Pacman(BufferedImage buffer, Laberinto laberinto, Ghost ghost) {
        this.laberinto = laberinto;
        this.ghost = ghost;
    }

    public void dibujarPacman(Graficos g) {
        g.fillCircle(x, y, pacmanSize, Color.yellow);
    }

    public void moverPacman() {
        // Coordenadas del fantasma según la matriz
        int[][] maze = laberinto.getLaberinto();
        int filaPacman = (y + pacmanSize + 1 / 2) / laberinto.getCeldaSize();
        int columnaPacman = (x + pacmanSize + 1 / 2) / laberinto.getCeldaSize();

        if (teclaPresionada[0] && filaPacman > 0) { // UP
            if (maze[filaPacman - 1][columnaPacman] != 1) { // Verifica la colisión
                y -= movimiento;
            }
        } else if (teclaPresionada[1] && columnaPacman > 0) { // LEFT
            if (maze[filaPacman][columnaPacman - 1] != 1) { // Verifica la colisión
                x -= movimiento;
            }
        } else if (teclaPresionada[2] && filaPacman < laberinto.getFilas() - 1) { // DOWN
            if (maze[filaPacman][columnaPacman] != 1) { // Verifica la colisión
                y += movimiento;
            } else {
                y--;
            }
        } else if (teclaPresionada[3] && columnaPacman < laberinto.getColumnas() - 1) { // RIGHT
            if (maze[filaPacman][columnaPacman] != 1) { // Verifica la colisión
                x += movimiento;
            } else {
                x--;
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

        //Verifica si está sobre un punto para comerlo
        if (maze[filaPacman][columnaPacman] == 0 || maze[filaPacman][columnaPacman] == 2) {
            laberinto.comerPunto(filaPacman, columnaPacman);
            //Comemos un punto grande
            if (maze[filaPacman][columnaPacman] == 2) {
                //Implementar el comer fantasmas
            }
        }

        if (x == ghost.getX() && y == ghost.getY()) {
            System.out.println("GAME OVER");
        }
        //System.out.println("Pacman X: " + x + " Y: " + y);
    }

    public void vidaMenos() {

    }

    public int getVidas() {
        return vidas;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
