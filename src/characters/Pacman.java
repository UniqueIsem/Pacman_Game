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
    private Timer colisionTimer;
    private final int tiempoColision = 500;
    private boolean enSuperPildora = false;
    private final int tiempoSuperPildora = 5000;
    private int vidas = 3;

    Laberinto laberinto;
    Ghost redGhost, orangeGhost, pinkGhost, cyanGhost;

    public Pacman(BufferedImage buffer, Laberinto laberinto, Ghost redGhost, Ghost orangeGhost, Ghost pinkGhost, Ghost cyanGhost) {
        this.laberinto = laberinto;
        this.redGhost = redGhost;
        this.orangeGhost = orangeGhost;
        this.pinkGhost = pinkGhost;
        this.cyanGhost = cyanGhost;

        // Inicializar el temporizador de colisión
        colisionTimer = new Timer(tiempoColision, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Restablecer la variable enColision después de tiempoColision milisegundos
                enColision = false;
                vidas--;
            }

        });
        colisionTimer.setRepeats(false);
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
        int xPacman = x; //Coordenadas de pacman
        int yPacman = y;
        //Coordenadas de fantasmas
        int xRedGhost = redGhost.getX();
        int yRedGhost = redGhost.getY();
        int xOrangeGhost = orangeGhost.getX();
        int yOrangeGhost = orangeGhost.getY();
        int xPinkGhost = pinkGhost.getX();
        int yPinkGhost = pinkGhost.getY();
        int xCyanGhost = cyanGhost.getX();
        int yCyanGhost = cyanGhost.getY();
        int rangoProximidad = 5;

        // Verificar si el fantasma rojo está dentro del rango de proximidad del Pacman
        if (Math.abs(xPacman - xRedGhost) <= rangoProximidad && Math.abs(yPacman - yRedGhost) <= rangoProximidad) {
            if (!enColision) {
                if (enSuperPildora) { //comer fantasma con superpildora
                    redGhost.setX(466);
                    redGhost.setY(246);
                } else if (vidas > 0) { //vida menos
                    System.out.println("colision rojo");
                    // Iniciar temporizador para restablecer la variable enColision después de cierto tiempo
                    colisionTimer.start();
                    detenerPacman();
                }
                enColision = true;

                return true;
            }
        } else {
            enColision = false;
        }

        // Verificar si el fantasma naranja está dentro del rango de proximidad del Pacman
        if (Math.abs(xPacman - xOrangeGhost) <= rangoProximidad && Math.abs(yPacman - yOrangeGhost) <= rangoProximidad) {
            if (!enColision) {
                if (enSuperPildora) {
                    orangeGhost.setX(466);
                    orangeGhost.setY(246);
                } else if (vidas > 0) {
                    detenerPacman();
                }
                enColision = true;
                System.out.println("colision naranja");
                // Iniciar temporizador para restablecer la variable enColision después de cierto tiempo
                colisionTimer.start();
                return true;
            }
        } else {
            enColision = false;
        }
        // Verificar si el fantasma rosa está dentro del rango de proximidad del Pacman
        if (Math.abs(xPacman - xPinkGhost) <= rangoProximidad && Math.abs(yPacman - yPinkGhost) <= rangoProximidad) {
            if (!enColision) {
                if (enSuperPildora) {
                    pinkGhost.setX(466);
                    pinkGhost.setY(246);
                } else if (vidas > 0) {
                    detenerPacman();
                }
                enColision = true;
                System.out.println("colision rosa");
                // Iniciar temporizador para restablecer la variable enColision después de cierto tiempo
                colisionTimer.start();
                return true;
            }
        } else {
            enColision = false;
        }
        // Verificar si el fantasma cyan está dentro del rango de proximidad del Pacman
        if (Math.abs(xPacman - xCyanGhost) <= rangoProximidad && Math.abs(yPacman - yCyanGhost) <= rangoProximidad) {
            if (!enColision) {
                if (enSuperPildora) {
                    cyanGhost.setX(466);
                    cyanGhost.setY(246);
                } else if (vidas > 0) {
                    detenerPacman();
                }
                enColision = true;
                System.out.println("colision cyan");
                // Iniciar temporizador para restablecer la variable enColision después de cierto tiempo
                colisionTimer.start();
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
            detenerPacman();
            redGhost.detenerGhost();
            orangeGhost.detenerGhost();
            pinkGhost.detenerGhost();
            cyanGhost.detenerGhost();
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
