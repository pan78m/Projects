import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MinesweeperGUI {
    private JButton[][] buttons;
    private int[][] board;
    private boolean[][] revealed;
    private int size;
    private int mines;
    private JFrame frame;
    private JPanel panel;

    public MinesweeperGUI(int size, int mines) {
        this.size = size;
        this.mines = mines;
        this.buttons = new JButton[size][size];
        this.board = new int[size][size];
        this.revealed = new boolean[size][size];

        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel = new JPanel(new GridLayout(size, size));
        frame.add(panel, BorderLayout.CENTER);

        initializeBoard();
        placeMines();
        calculateAdjacentMines();

        createButtons();

        frame.pack();
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = 0;
                revealed[i][j] = false;
            }
        }
    }

    private void placeMines() {
        int count = 0;
        Random random = new Random();
        while (count < mines) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            if (board[x][y] != -1) {
                board[x][y] = -1;
                count++;
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private int countAdjacentMines(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int newX = x + i;
                int newY = y + j;
                if (isValid(newX, newY) && board[newX][newY] == -1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != -1) {
                    board[i][j] = countAdjacentMines(i, j);
                }
            }
        }
    }

    private void revealCell(int x, int y) {
        if (!isValid(x, y) || revealed[x][y]) {
            return;
        }
        revealed[x][y] = true;
        buttons[x][y].setEnabled(false);
        if (board[x][y] == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newX = x + i;
                    int newY = y + j;
                    if (isValid(newX, newY) && !revealed[newX][newY]) {
                        revealCell(newX, newY);  // Backtracking here
                    }
                }
            }
        } else if (board[x][y] == -1) {
            buttons[x][y].setText("*");
            gameOver();
        } else {
            buttons[x][y].setText(String.valueOf(board[x][y]));
        }
        if (isGameFinished()) {
            gameWon();
        }
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(frame, "Game Over! You stepped on a mine.", "Game Over", JOptionPane.ERROR_MESSAGE);
        disableButtons();
    }

    private void gameWon() {
        JOptionPane.showMessageDialog(frame, "Congratulations! You won the game.", "Game Won", JOptionPane.INFORMATION_MESSAGE);
        disableButtons();
    }

    private boolean isGameFinished() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!revealed[i][j] && board[i][j] != -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void createButtons() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(40, 40));
                buttons[i][j].addActionListener(new ButtonListener(i, j));
                panel.add(buttons[i][j]);
            }
        }
    }

    private void disableButtons() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        private int x;
        private int y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (board[x][y] == 0) {
                revealCellWithBacktrack(x, y);
            } else {
                revealCell(x, y);
            }
        }
    }

    private void revealCellWithBacktrack(int x, int y) {
        if (!isValid(x, y) || revealed[x][y]) {
            return;
        }
        revealed[x][y] = true;
        buttons[x][y].setEnabled(false);

        if (board[x][y] == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newX = x + i;
                    int newY = y + j;
                    revealCellWithBacktrack(newX, newY);
                }
            }
        } else if (board[x][y] == -1) {
            buttons[x][y].setText("*");
            gameOver();
        } else {
            buttons[x][y].setText(String.valueOf(board[x][y]));
        }
        if (isGameFinished()) {
            gameWon();
        }
    }

    public static void main(String[] args) {
        int size = 8;
        int mines = 10;
        SwingUtilities.invokeLater(() -> new MinesweeperGUI(size, mines));
    }
}