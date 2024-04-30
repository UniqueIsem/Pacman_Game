package main;

import componentes.Laberinto;
import characters.Pacman;
import graficos.Graficos;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Game extends JFrame {

    private int width = 886;
    private int height = 548;
    
    Laberinto laberinto = new Laberinto();
    Pacman pacman = new Pacman();
    Graficos graficos = new Graficos(width, height);

    public Game() {
        initComponents();
        laberinto.dibujar(graficos);
        pacman.dibujar(graficos);
        
    }

    private void initComponents() {
        JFrame frame = new JFrame("Pacman");

        frame.add(graficos);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                pacman.movimientoPacman(graficos, e);
                pacman.dibujar(graficos);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
    }
}
