import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private Random random = new Random();
    private int number;
    private int maxAttempts = 10;
    private int attempts;
    private int score = 0;
    private int rounds = 0;

    private JLabel instructionLabel = new JLabel("I have generated a number between 1 and 100.");
    private JTextField guessField = new JTextField(10);
    private JButton guessButton = new JButton("Guess");
    private JLabel feedbackLabel = new JLabel("");
    private JLabel scoreLabel = new JLabel("Score: 0");
    private JLabel roundsLabel = new JLabel("Rounds played: 0");
    private JButton playAgainButton = new JButton("Play Again");
    private JButton exitButton = new JButton("Exit");

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        instructionLabel.setFont(new Font("Serif", Font.BOLD, 16));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setForeground(new Color(60, 63, 65));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(instructionLabel, gbc);

        guessField.setFont(new Font("Serif", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(guessField, gbc);

        guessButton.setFont(new Font("Serif", Font.BOLD, 16));
        guessButton.setBackground(new Color(70, 130, 180));
        guessButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        add(guessButton, gbc);

        feedbackLabel.setFont(new Font("Serif", Font.BOLD, 16));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(feedbackLabel, gbc);

        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        gbc.gridy = 3;
        add(scoreLabel, gbc);

        roundsLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        gbc.gridy = 4;
        add(roundsLabel, gbc);

        playAgainButton.setFont(new Font("Serif", Font.BOLD, 16));
        playAgainButton.setBackground(new Color(34, 139, 34));
        playAgainButton.setForeground(Color.WHITE);
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(playAgainButton, gbc);

        exitButton.setFont(new Font("Serif", Font.BOLD, 16));
        exitButton.setBackground(new Color(178, 34, 34));
        exitButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        add(exitButton, gbc);

        guessButton.addActionListener(new GuessButtonListener());
        playAgainButton.addActionListener(new PlayAgainButtonListener());
        exitButton.addActionListener(e -> System.exit(0));

        startNewGame();
    }

    private void startNewGame() {
        number = random.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("");
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                
                if (guess < number) {
                    feedbackLabel.setText("Too low!");
                } else if (guess > number) {
                    feedbackLabel.setText("Too high!");
                } else {
                    feedbackLabel.setText("Congratulations! You've guessed the correct number!");
                    score += (maxAttempts - attempts + 1);
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                }

                if (attempts >= maxAttempts && guess != number) {
                    feedbackLabel.setText("Sorry, you've used all your attempts. The correct number was " + number + ".");
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                }

                scoreLabel.setText("Score: " + score);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            rounds++;
            roundsLabel.setText("Rounds played: " + rounds);
            startNewGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameGUI game = new NumberGuessingGameGUI();
            game.setVisible(true);
        });
    }
}
