package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Pacman {

    private int x = 466;
    private int y = 316;
    private int pacmanSize = 13;
    private int movimiento = 2;
    public boolean[] teclaPresionada = new boolean[4];
    private boolean enColision = false;
    private boolean enSuperPildora = false;
    private final int tiempoColision = 3000;
    private final int tiempoSuperPildora = 5000;
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

        //Verifica si está sobre un punto pequeño o grande para comerlo
        if (maze[filaPacman][columnaPacman] == 0) {
            laberinto.eatPill(filaPacman, columnaPacman);
        } else if (maze[filaPacman][columnaPacman] == 2) {
            laberinto.eatPill(filaPacman, columnaPacman);
            movimiento = 4;
            enSuperPildora = true;
            tiempoSuperPildora();
        }

        //Verifica si hay colision con el fantasma
        if (ghostTouch()) {
            isGameOver();
        }
    }

    public boolean ghostTouch() {
        int xPacman = x;
        int yPacman = y;
        int xFantasma = ghost.getX();
        int yFantasma = ghost.getY();
        int rangoProximidad = 5;

        // Verificar si el fantasma está dentro del rango de proximidad del Pacman
        if (Math.abs(xPacman - xFantasma) <= rangoProximidad && Math.abs(yPacman - yFantasma) <= rangoProximidad) {
            if (!enColision) {
                if (enSuperPildora) {
                    ghost.setX(466);
                    ghost.setY(246);
                } else if (vidas > 0) {
                    detenerPacman();
                    vidas--;
                }
                enColision = true;
                // Iniciar temporizador para restablecer la variable enColision después de cierto tiempo
                Timer timer = new Timer(tiempoColision, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Restablecer la variable enColision después de tiempoColision milisegundos
                        enColision = false;
                    }
                });
                timer.setRepeats(false);
                timer.start();
                return true;
            }
        } else {
            enColision = false;
        }
        return false;
    }

    public void tiempoSuperPildora() {
        Timer timer = new Timer(tiempoSuperPildora, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Restablece la variable para quitar superpildora
                enSuperPildora = false;
                movimiento = 2;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public boolean isGameOver() {
        if (vidas == 0) {
            reiniciarPosicion();
            ghost.reiniciarPosicion();
            return true;
        }
        return false;
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

    public boolean isSuperPildora() {
        return enSuperPildora;
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
