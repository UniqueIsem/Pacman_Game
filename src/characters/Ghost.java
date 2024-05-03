package characters;

import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Ghost {

    private int x = 435;
    private int y = 250;
    private final int ghostSize = 15;
    private final int ancho = 30;
    private final int alto = 30;
    private final int movimiento = 10;
    private Color[] colors = new Color[4];

    private BufferedImage buffer;
    private Graphics gBuffer;
    
    public Ghost(BufferedImage buffer){
        this.buffer = buffer;
        this.gBuffer = buffer.createGraphics();
    }
    
    public void dibujarFantasmas(Graficos g) {
        colors[0] = Color.red;
        colors[1] = Color.orange;
        colors[2] = Color.pink;
        colors[3] = Color.cyan;
        for (int i = 0; i < 3; i++) {
            repetirFantasmas(g, colors[i] );
        }
    }
    
    public void repetirFantasmas(Graficos g, Color c) {
        //BODY AND EYES
        g.fillRect(x - 15, y - 12, x + 15, y + 12, c);
        g.fillCircle(x - 8, y, 5, Color.white);
        g.fillCircle(x - 6, y, 3, Color.black);
        g.fillCircle(x + 8, y, 5, Color.white);
        g.fillCircle(x + 6, y, 3, Color.black);
        
    }
}
