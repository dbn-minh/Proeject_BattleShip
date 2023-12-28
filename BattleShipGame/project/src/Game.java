import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {

    private GamePanel gamePanel;

    public Game() {
        // Choose the AI Difficulty
        String[] options = new String[]{"Easy", "Medium", "Hard"};
        String message = "Easy will make moves entirely randomly,\nMedium will focus on areas where it finds ships,"
                + "\nand Hard will make smarter choices over Medium.";

        // Set the font and size for the options in the JOptionPane
        Font font = new Font("Times New Roman", Font.PLAIN, 16);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
    
        
        int difficultyChoice = JOptionPane.showOptionDialog(null, message,
                "Choose an AI Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        gamePanel = new GamePanel(difficultyChoice);

        // Set the background color based on the chosen difficulty
        

        frame.getContentPane().add(gamePanel);

        frame.addKeyListener(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.handleInput(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void start() {
    }
}