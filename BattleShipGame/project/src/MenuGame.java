import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class MenuGame {
    private JFrame frame;

    public MenuGame() {
        frame = new JFrame("Battleship Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 1024);
        frame.setLayout(new BorderLayout());

        // Create a JLabel for the background image
        ImageIcon backgroundImage = new ImageIcon("BattleShipGame/project/Image/background.jpg"); // Replace with the
                                                                                                  // actual path to your
                                                                                                  // image file
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Battleship Game");
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon startImage = new ImageIcon("BattleShipGame/project/Image/start.png"); // Replace with the actual path
                                                                                        // to your
        // start game image file
        JButton startButton = new JButton(startImage);
        startButton.setPreferredSize(new Dimension(startImage.getIconWidth(), startImage.getIconHeight()));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButtonClicked();
            }
        });

        ImageIcon exitImage = new ImageIcon("BattleShipGame/project/Image/exit.png"); // Replace with the actual path to
                                                                                      // your
        // exit image file
        JButton exitButton = new JButton(exitImage);
        exitButton.setPreferredSize(new Dimension(exitImage.getIconWidth(), exitImage.getIconHeight()));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonClicked();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false); // Make the panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Add spacing between buttons

        buttonPanel.add(startButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(exitButton, gbc);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // Make the panel transparent
        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createVerticalGlue());
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());

        backgroundLabel.add(panel, BorderLayout.CENTER); // Add the panel to the background label

        frame.setContentPane(backgroundLabel); // Set the background label as the content pane

        frame.setVisible(true);
    }

    private void startButtonClicked() {
        startGame();
    }

    private void exitButtonClicked() {
        exitGame();
    }

    private void startGame() {
        frame.dispose();

        // Create the game instance
        Game game = new Game();

        // Start the game
        game.start();
    }

    private void exitGame() {
        frame.dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuGame();
            }
        });
    }
}