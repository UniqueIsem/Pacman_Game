package characters;

import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pacman {

    //Laberinto lab = new Laberinto();
    private int x = 435;
    private int y = 315;
    public boolean[] teclaPresionada = new boolean[4];
    private int pacmanSize = 15;
    private int vidas = 3;
    private final int movimiento = 10;
    
    private BufferedImage buffer;
    private Graphics gBuffer;

    public Pacman(BufferedImage buffer) {
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
    }
    
    public void dibujarPacman(Graficos g) {
        g.limpiarBuffer();
        g.fillCircle(x, y, pacmanSize, Color.yellow);
        g.drawCircle(x, y, pacmanSize, Color.black);
    }
    
    public void moverPacman() {
        if (teclaPresionada[0]) {
            y -= movimiento;
            System.out.println("X: " + x + " Y: " + y + " UP");
        }
        if (teclaPresionada[1]) {
            x -= movimiento;
            System.out.println("X: " + x + " Y: " + y + " LEFT");
        }
        if (teclaPresionada[2]) {
            y += movimiento;
            System.out.println("X: " + x + " Y: " + y + " DOWN");
        }
        if (teclaPresionada[3]) {
            x += movimiento;
            System.out.println("X: " + x + " Y: " + y + " RIGHT");
        }
    }
}
