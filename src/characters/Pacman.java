package characters;

import graficos.Graficos;
import java.awt.Color;
import java.awt.Image;

public class Pacman {

    //Laberinto lab = new Laberinto();
    private int x = 435;
    private int y = 315;
    public boolean[] teclaPresionada = new boolean[4];
    private int pacmanSize = 15;
    private int vidas = 3;
    private final int movimiento = 3;
    Image temp;

    public void dibujar(Graficos g) {
        dibujarPacman(g);
    }

    public void dibujarPacman(Graficos g) {
        g.fillCircle(x, y, pacmanSize, Color.yellow);
        g.drawCircle(x, y, pacmanSize, Color.black);        
    }
    
    public void moverPacman() {
        if (teclaPresionada[0]) {
            y -= movimiento;
        }
        if (teclaPresionada[1]) {
            x -= movimiento;
        }
        if (teclaPresionada[2]) {
            y += movimiento;
        }
        if (teclaPresionada[3]) {
            x += movimiento;
        }
    }
}
