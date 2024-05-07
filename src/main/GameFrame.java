package main;

import java.awt.*;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private int width = 886; 
    private int height = 590; //548
    GamePanel gPanel;

    public GameFrame() {
        initComponents();
    }

    private void initComponents() {
        gPanel = new GamePanel(width, height);
        add(gPanel);
        
        setTitle("Pacman");
        setBackground(Color.BLACK);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
