package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Pacman {

    private int x = 466;
    private int y = 316;
    private int pacmanSize = 13;
    private final int movimiento = 2;
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
        int filaPacman = (y + pacmanSize - 1 / 2) / laberinto.getCeldaSize();
        int columnaPacman = (x + pacmanSize - 1 / 2) / laberinto.getCeldaSize();

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
                y -= movimiento;
                detenerPacman();
            }
        } else if (teclaPresionada[3] && columnaPacman < laberinto.getColumnas() - 1) { // RIGHT
            if (maze[filaPacman][columnaPacman] != 1) { // Verifica la colisión
                x += movimiento;
            } else {
                x -= movimiento;
                detenerPacman();
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
            //Si comemos un punto grande
            if (maze[filaPacman][columnaPacman] == 2) {
                //Implementar comer fantasmas
            }
        }

        //Verifica si hay colision con el fantasma
        if (ghostTouch()) {
            gameOver();
        }
        //System.out.println("Pacman X: " + x + " Y: " + y);
    }

    public boolean ghostTouch() {
        if (x == ghost.getX() + 4 && y == ghost.getY() + 5) {
            return true;
        } else if (x == ghost.getX()) {
            
        }
        return false;
    }

    public boolean gameOver() {
        detenerPacman();
        if (vidas > 0) {
            System.out.println("colision");
            vidas--;
        }
        if (vidas == 0) {
            reiniciarPosicion();
            //vidas = 3;
            return true;
        } else {
            return false;
        }
    }

    public void reiniciarPosicion() {
        x = 465;
        y = 315;
    }

    public void detenerPacman() {
        teclaPresionada[0] = false;
        teclaPresionada[1] = false;
        teclaPresionada[2] = false;
        teclaPresionada[3] = false;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
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
