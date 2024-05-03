package main;

import characters.Pacman;
import componentes.Laberinto;
import graficos.Graficos;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private final int width, height;
    public BufferedImage buffer;
    Graphics gBuffer;
    Thread th;
    Image img;

    Graficos graficos;
    Laberinto laberinto;
    Pacman pacman;

    public GamePanel(int w, int h) {
        this.width = w;
        this.height = h;

        if (buffer == null) {
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }

        makeGraphics();
        makeMaze();
        makePacman();

        th = new Thread(this);
        th.start();
        
        addKeyListener(this);

        setFocusable(true);
        requestFocus();
    }

    private void makeGraphics() {
        graficos = new Graficos(width, height, buffer);
    }

    private void makeMaze() {
        laberinto = new Laberinto(buffer);
    }

    private void makePacman() {
        pacman = new Pacman(buffer);
    }

    @Override
    public void paintComponent(Graphics g) {
        img = createImage(getWidth(), getHeight());
        gBuffer = img.getGraphics();

        laberinto.drawMaze(graficos);

        g.drawImage(buffer, 0, 0, this);
    }

    private void showComponents() {
        System.out.println("show components");
    }

    @Override
    public void run() {
        while (true) {
            pacman.dibujarPacman(graficos);
            pacman.moverPacman();
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                pacman.teclaPresionada[0] = true;
                pacman.teclaPresionada[1] = false;
                pacman.teclaPresionada[2] = false;
                pacman.teclaPresionada[3] = false;
                break;
            case KeyEvent.VK_A:
                pacman.teclaPresionada[0] = false;
                pacman.teclaPresionada[1] = true;
                pacman.teclaPresionada[2] = false;
                pacman.teclaPresionada[3] = false;
                break;
            case KeyEvent.VK_S:
                pacman.teclaPresionada[0] = false;
                pacman.teclaPresionada[1] = false;
                pacman.teclaPresionada[2] = true;
                pacman.teclaPresionada[3] = false;
                break;
            case KeyEvent.VK_D:
                pacman.teclaPresionada[0] = false;
                pacman.teclaPresionada[1] = false;
                pacman.teclaPresionada[2] = false;
                pacman.teclaPresionada[3] = true;
                break;
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
