package characters;

import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost {

    private int x = 435;
    private int y = 250;
    private int movimiento = 20;
    private Color color;

    private BufferedImage buffer;
    private Graphics gBuffer;

    public Ghost(BufferedImage buffer) {
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
    }

    public void moverFantasma() {
        Random random = new Random();
        int direccion = random.nextInt(4);
        
        switch (direccion) {
            case 0:
                y -= movimiento;
                break;
            case 1: // Abajo
                y += movimiento;
                break;
            case 2: // Izquierda
                x -= movimiento;
                break;
            case 3: // Derecha
                x += movimiento;
                break;
        }
    }

    public void dibujarFantasma(Graficos g, Color c) {
        //BODY AND EYES
        g.fillRect(x - 15, y - 12, x + 15, y + 12, c);
        g.fillCircle(x - 8, y, 5, Color.white);
        g.fillCircle(x - 6, y, 3, Color.black);
        g.fillCircle(x + 8, y, 5, Color.white);
        g.fillCircle(x + 6, y, 3, Color.black);

    }
}
