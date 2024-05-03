package main;

import componentes.Laberinto;
import characters.Pacman;
import graficos.Graficos;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas {

    private int width = 886;
    private int height = 548;

    Laberinto laberinto = new Laberinto();
    Pacman pacman = new Pacman();

    Graficos bufMap = new Graficos(width, height);
    Graficos bufMov = new Graficos(width, height);

    public Game() {
        initComponents();

        bufMap.maze();
        bufMov.pacman();

        //laberinto.dibujar(bufMap);
        //pacman.dibujar(g);
    }

    private void initComponents() {
        JFrame frame = new JFrame("Pacman");
        JPanel panel = new JPanel();

        panel.setLayout(null);
        
        bufMap = bufMov;
        panel.add(bufMov);
        panel.add(bufMap);
        panel.setBackground(Color.BLACK);

        frame.add(panel);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener() {
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
        });
        Thread animationThread = new Thread(() -> {
            while (true) {
                // Mover el Pacman continuamente
                pacman.moverPacman();
                pacman.dibujar(bufMov);
                // Actualizar la pantalla
                repaint();
                // Pausa para controlar la velocidad de la animación
                try {
                    Thread.sleep(10); // Ajusta el valor según la velocidad deseada
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        animationThread.start();
    }
}
