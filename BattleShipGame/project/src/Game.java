import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Game implements KeyListener {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        Game game = new Game();
    }

    private GamePanel gamePanel;

    public Game() {
        String[] options = new String[]{"Easy", "Medium", "Hard"};
        String message = "Easy will make moves entirely randomly,\nMedium will focus on areas where it finds ships,\nand Hard will make smarter choices over Medium.";

        UIManager.put("OptionPane.buttonFont", new Font("Times New Roman", Font.PLAIN, 16));
        UIManager.put("OptionPane.messageFont", new Font("Times New Roman", Font.PLAIN, 16));
        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));

        int difficultyChoice = JOptionPane.showOptionDialog(null, message,
                "Choose an AI Difficulty",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        JFrame frame = new JFrame("Battleship") {
            @Override
            public void setDefaultCloseOperation(int operation) {
                if (operation == JFrame.EXIT_ON_CLOSE) {
                    addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
                } else {
                    super.setDefaultCloseOperation(operation);
                }
            }
        };
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        gamePanel = new GamePanel(difficultyChoice);
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not implemented
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Not implemented
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not implemented
    }

    public void start() {
    }
}