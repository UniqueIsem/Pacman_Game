package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.Timer;

public class Ghost {

    private int x = 466;
    private int y = 246;
    private final int ghostSize = 13;
    private final int movimiento = 2;
    private boolean detenido = false;
    private final int tiempoDeReaccionDetenido = 10;
    Random random = new Random();
    int direccion;

    Laberinto laberinto;

    public Ghost(BufferedImage buffer, Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    public void dibujarFantasma(Graficos g, boolean superPidora) {
        if (!superPidora) {
            //Ghost body
            g.fillCircle(x, y, ghostSize, Color.red);
            //Ghost eyes
            g.fillCircle(x - 8, y, 5, Color.white);
            g.fillCircle(x - 6, y, 3, Color.black);
            g.fillCircle(x + 8, y, 5, Color.white);
            g.fillCircle(x + 6, y, 3, Color.black);
        } else {
            //Ghost body
            g.fillCircle(x, y, ghostSize, Color.BLUE);
            g.drawDottedCircle(x, y, ghostSize + 4, Color.white);
            //Ghost eyes
            g.fillCircle(x - 8, y, 5, Color.white);
            g.fillCircle(x + 8, y, 5, Color.white);
        }
    }

    public void moverFantasma() {
        if (!detenido) {
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

            //Actualiza las coordenadas de x,y por si acaso
            int nuevaX = x;
            int nuevaY = y;
            setX(nuevaX);
            setY(nuevaY);
        }
    }

    public void cambioDeDireccion() {
        // Cambio de direccion del fantasma de manera aleatorea
        direccion = random.nextInt(4);
    }

    public void detenerGhost() {
        if (!detenido) {
            Timer timer = new Timer(tiempoDeReaccionDetenido, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Establece la variable para que se detenga
                    detenido = true;
                    Timer timerReanudar = new Timer(0, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            detenido = false;
                        }
                    });
                    timerReanudar.setInitialDelay(2000); // Espera 2000 milisegundos antes de comenzar
                    timerReanudar.setRepeats(false); // No se repite
                    timerReanudar.start();
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

    }

    public void reiniciarPosicion() {
        x = 466;
        y = 246;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}
