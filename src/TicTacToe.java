import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {
    private JFrame window;
    private JButton[][] buttons;
    private String[][] board;
    private String currentPlayer;
    private JLabel turnLabel;

    public TicTacToe() {
        // Set up the window and game board
        window = new JFrame("Tic-Tac-Toe");
        window.setSize(500, 500);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        board = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50)); // set font size to 50
                buttons[i][j].setBackground(Color.WHITE); // set cell background to white
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                gameBoard.add(buttons[i][j]);
                board[i][j] = "";
            }
        }

        // Set up the turn label and new game button
        currentPlayer = "X";
        turnLabel = new JLabel("Player X's turn", JLabel.CENTER);
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // set font size to 20
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameListener());

        // Add the components to the window
        window.add(turnLabel, BorderLayout.NORTH);
        window.add(gameBoard, BorderLayout.CENTER);
        window.add(newGameButton, BorderLayout.SOUTH);

        // Display the window
        window.setVisible(true);
    }

    // ActionListener for the game board buttons
    private class ButtonListener implements ActionListener {
        private int row;
        private int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            // Update the board with the current player's move
            buttons[row][col].setText(currentPlayer);
            buttons[row][col].setEnabled(false); // If a button is enabled, it can be clicked and will respond to user interactions.
            board[row][col] = currentPlayer;

            // Check for a win or tie
            checkForWin();
            switchPlayers();
        }
    }


    // ActionListener for the new game button
    private class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            resetGame();
        }
    }

    // Switch to the other player's turn
    public void switchPlayers() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
            turnLabel.setText("Player O's turn");
            turnLabel.setForeground(Color.BLUE); // set label color to blue
        } else {
            currentPlayer = "X";
            turnLabel.setText("Player X's turn");
            turnLabel.setForeground(Color.RED); // set label color to red
        }
    }

    // Check for a win or tie
    public void checkForWin() {
        // Check for horizontal wins
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                endGame(board[i][0]);
                return;
            }
        }
        // Check for vertical
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                endGame(board[0][i]);
                return;
            }
        }
        // Check for diagonal wins
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            endGame(board[0][0]);
            return;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            endGame(board[0][2]);
            return;
        }
        // Check for a tie
        boolean isTie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("")) {
                    isTie = false;
                    break;
                }
            }
            if (!isTie) {
                break;
            }
        }
        if (isTie) {
            endGame("tie");
        }
    }

    // End the game and display a message
    public void endGame(String winner) {
        if (winner.equals("tie")) {
            JOptionPane.showMessageDialog(window, "It's a tie!");
        } else {
            JOptionPane.showMessageDialog(window, "Player " + winner + " wins!");
        }
        resetGame();
    }

    // Reset the game board and turn label
    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                board[i][j] = "";
            }
        }
        currentPlayer = "X";
        turnLabel.setText("Player X's turn");
        turnLabel.setForeground(Color.RED); // set label color to red
    }
}