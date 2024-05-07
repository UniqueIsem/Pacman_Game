package main;

import characters.Ghost;
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
    Thread th; //hilo encargado de repintar
    Image img;

    Graficos graficos;
    Laberinto laberinto;
    Pacman pacman;
    Ghost ghost;

    public GamePanel(int w, int h) {
        this.width = w;
        this.height = h;

        if (buffer == null) {
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }

        makeGraphics();
        makeMaze();
        makePacman();
        makeGhosts();

        // Hilo encargado del movimiento de pacman
        th = new Thread(this);
        th.start();
        
        // Movimiento continuo de pacman
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
        pacman = new Pacman(buffer, laberinto);
    }

    private void makeGhosts() {
        ghost = new Ghost(buffer);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        img = createImage(getWidth(), getHeight());
        gBuffer = img.getGraphics();

        //Repinta comonentes del laberinto y puntos en el mapa
        laberinto.drawMaze(graficos);
        laberinto.drawPoints(graficos, pacman);

        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void run() {
        while (true) {
            //Dibujar pacman
            pacman.dibujarPacman(graficos);
            pacman.moverPacman();
            //Dibujar fantasmas
            ghost.dibujarFantasmas(graficos);
            //Implementar metodo para mover fantasmas
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

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
