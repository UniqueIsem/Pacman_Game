package characters;

import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Pacman {

    //Laberinto lab = new Laberinto();
    private int x = 435;
    private int y = 315;
    private int pacmanSize = 15;
    private int vidas = 3;
    private final int movimiento = 3;

    public void dibujar(Graficos g) {
        g.fillCircle(x, y, pacmanSize, Color.yellow);
        g.drawCircle(x, y, pacmanSize, Color.black);
    }

    public void movimientoPacman(Graficos g, KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                y -= movimiento; //y = y - movimiento
                System.out.println("UP");
                break;
            case KeyEvent.VK_A:
                x -= movimiento;
                System.out.println("LEFT");
                break;
            case KeyEvent.VK_S:
                y += movimiento;
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_D:
                x += movimiento;
                System.out.println("RIGHT");
                break;
            default:
                break;
        }
        g.repaint();
    }
}
