package main;

import characters.Ghost;
import characters.Pacman;
import componentes.Laberinto;
import componentes.Tablero;
import graficos.Graficos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

    private final int width, height;
    public BufferedImage buffer;
    Graphics gBuffer;
    Image img;

    private int ghostMov = 80;

    Thread thPacman;
    Thread thCambioDireccion;
    Thread thGhost1, thGhost2, thGhost3, thGhost4;

    Graficos graficos;
    Laberinto laberinto;
    Tablero tablero;
    Pacman pacman;
    Ghost ghost;

    public GamePanel(int w, int h) {
        this.width = w;
        this.height = h;
        if (buffer == null) {
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }
        this.ghost = new Ghost(buffer);

        makeGraphics();
        makeMaze();
        makeBoard();
        makePacman();
        //makeGhosts();

        iniciarHilos();

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

    private void makeBoard() {
        tablero = new Tablero(buffer);
    }

    private void makePacman() {
        pacman = new Pacman(buffer, laberinto);
    }

    private synchronized void iniciarHilos() {
        //Encarcados de creacion y movimiento (pacman y fantasmas)
        thPacman = new Thread(this::runPacman);
        thCambioDireccion = new Thread(this::cambioDireccion);
        thGhost1 = new Thread(this::runGhost1);
        thGhost2 = new Thread(this::runGhost2);
        thGhost3 = new Thread(this::runGhost3);
        thGhost4 = new Thread(this::runGhost4);
        thPacman.start();
        thCambioDireccion.start();
        thGhost1.start();
        thGhost2.start();
        thGhost3.start();
        thGhost4.start();
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

    public void runPacman() {
        while (true) {
            //Dibujar pacman
            pacman.dibujarPacman(graficos);
            pacman.moverPacman();
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cambioDireccion() {
        while (true) {
            ghost.cambioDeDireccion();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runGhost1() {
        ghost = new Ghost(buffer);
        while (true) {
            ghost.dibujarFantasma(graficos, Color.red);
            ghost.moverFantasma(laberinto);
            repaint();
            try {
                Thread.sleep(ghostMov);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runGhost2() {
        ghost = new Ghost(buffer);
        while (true) {
            ghost.dibujarFantasma(graficos, Color.orange);
            ghost.moverFantasma(laberinto);
            repaint();
            try {
                Thread.sleep(ghostMov);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runGhost3() {
        ghost = new Ghost(buffer);
        while (true) {
            ghost.dibujarFantasma(graficos, Color.pink);
            ghost.moverFantasma(laberinto);
            repaint();
            try {
                Thread.sleep(ghostMov);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void runGhost4() {
        ghost = new Ghost(buffer);
        while (true) {
            ghost.dibujarFantasma(graficos, Color.cyan);
            ghost.moverFantasma(laberinto);
            repaint();
            try {
                Thread.sleep(ghostMov);
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
