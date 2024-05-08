package characters;

import componentes.Laberinto;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class GhostFactory {

    private Laberinto laberinto;

    public GhostFactory(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    public Ghost createRedGhost(BufferedImage buffer) {
        return new Ghost(buffer, laberinto, Color.RED);
    }

    public Ghost createOrangeGhost(BufferedImage buffer) {
        return new Ghost(buffer, laberinto, Color.ORANGE);
    }

    public Ghost createPinkGhost(BufferedImage buffer) {
        return new Ghost(buffer, laberinto, Color.PINK);
    }

    public Ghost createCyanGhost(BufferedImage buffer) {
        return new Ghost(buffer, laberinto, Color.CYAN);
    }
}
