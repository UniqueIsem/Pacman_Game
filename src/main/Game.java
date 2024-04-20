package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {
    private int width = 540;
    private int height = 920;
    
    public Game() {
        initComponents();
    }
    


    private void initComponents() {
        JFrame frame = new JFrame("Pacman");
        Game game = new Game();
        frame.add(game);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
}
