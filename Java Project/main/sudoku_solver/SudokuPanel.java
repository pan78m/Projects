import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SudokuPanel extends JPanel {
    public int count; // For counting the number of backtracking
    public static final int SIZE = 9; // The size of grid

    public static int[][] get = new int[SIZE][SIZE];
    public static int[][] tempGet = new int[SIZE][SIZE];
    static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    JTextField[][] textField; // For creating a 9*9 blocks
    JButton button1;
    JButton button2;
    JButton button3;
    Font font; // Setting the fonts
    Timer timer;
    boolean isRunning = false;

    // Constructor
    SudokuPanel() {

        get = new SudokuGenerator().getSudokuPuzzle();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tempGet[i][j] = get[i][j];
            }
        }

        JFrame frame = new JFrame("Sudoku Solver"); // Create a frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminating the program
        font = new Font("SansSerif", Font.BOLD, 40);
        textField = new JTextField[9][9]; // Create a array[9][9] to hold values
        button1 = new JButton("solve"); // Create a button
        button2 = new JButton("Generate Puzzle"); // Create a button
        button3 = new JButton("Clear"); // Create a button
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT - 30)); // Setting the size of panel
        GridLayout grid = new GridLayout(10, 9); // Creating a 10*9 grids(1 for button)
        setLayout(grid); // Add the gridLayout
        frame.setContentPane(this); // add panel to frame

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                add(textField[i][j] = new JTextField(5)); // Add array[9][9] to panel with size 5
                if (get[i][j] == 0) {
                    textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                    // make values to be align center
                    textField[i][j].setFont(font); // set the font
                } else {
                    textField[i][j].setText("" + get[i][j]);// add values into text field
                    textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                    // make values to be align center
                    textField[i][j].setFont(font);// set the font
                }
            }
        }

        add(button1); // Add Generaete puzzel button to panel
        add(button2); // Add solve button to panel

        button3.setBackground(Color.DARK_GRAY);
        button3.setForeground(Color.GRAY);

        add(button3);// Add clear button

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (textField[i][j].getText().equals("")) {
                            get[i][j] = 0;
                        } else
                            get[i][j] = Integer.parseInt(textField[i][j].getText());
                    }
                }

                if (solveSudoku()) {
                    System.out.println("Number of Back trackings:" + count);
                    printSudoku();

                } else
                    System.out.println("No solution");
            }

            public void printSudoku() { // Printing the Sudoku
                timer = new Timer();

                TimerTask task = new TimerTask() {
                    int i = 0;
                    int j = 0;

                    @Override
                    public void run() {
                        if (j < 9) {
                            if (i < 9) {
                                textField[i][j].setText("" + get[i][j]); // Print the values on to board
                                textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                                textField[i][j].setFont(font);
                                i++;
                            } else {
                                i = 0;
                                j++;
                            }

                        } else {
                            timer.cancel();
                            isRunning = false;
                        }
                    }
                };

                timer.scheduleAtFixedRate(task, 0, 300);
                isRunning = true;
            }

            public boolean solveSudoku() {
                int row = 0;
                int col = 0;
                int[] a = FindEmptyLocation(row, col);
                // if there is no empty locations then the sudoku is already solved
                if (a[0] == 0)
                    return true;
                row = a[1]; // get the position of empty row
                col = a[2]; // get the position of empty column
                for (int i = 1; i <= SIZE; i++) // attempt to fill number between 1 to 9
                {
                    if (NumberIsSafe(i, row, col)) // Check the number i can be filled or not into empty location
                    {
                        get[row][col] = i; // Fill the number i
                                           // Backtracking
                        if (solveSudoku())
                            return true;
                        count++; // For knowing the number of times backtracking happens

                        get[row][col] = 0; // Solution is wrong Reassign the value
                    }
                }
                return false;
            }

            public int[] FindEmptyLocation(int row, int col) // Finding the empty location
            {
                int flag = 0;
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (get[i][j] == 0) // Check cell is empty
                        {
                            row = i; // Changing the values of row and col
                            col = j;
                            flag = 1;
                            int[] a = { flag, row, col };
                            return a;
                        }
                    }
                }
                int[] a = { flag, -1, -1 };
                return a;
            }

            public boolean NumberIsSafe(int n, int r, int c) {

                for (int i = 0; i < SIZE; i++) { // Checking in col
                    if (get[r][i] == n) // If there is a cell with value equal to i
                        return false;
                }

                for (int i = 0; i < SIZE; i++) { // Checking row
                    if (get[i][c] == n) // If there is a cell with value equal to i
                        return false;
                }

                int row_start = (r / 3) * 3;
                int col_start = (c / 3) * 3;
                for (int i = row_start; i < row_start + 3; i++) { // Checking sub matrix
                    for (int j = col_start; j < col_start + 3; j++) {
                        if (get[i][j] == n)
                            return false;
                    }
                }
                return true;
            }

        }); // End of action performed

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                get = new SudokuGenerator().getSudokuPuzzle();

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        tempGet[i][j] = get[i][j];
                    }
                }

                if (isRunning) {
                    timer.cancel();
                    isRunning = false;
                }

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (get[i][j] == 0) {
                            textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                            // make values to be align center
                            textField[i][j].setFont(font); // set the font
                            textField[i][j].setText("");
                        } else {
                            textField[i][j].setText("" + get[i][j]);// add values into text field
                            textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                            // make values to be align center
                            textField[i][j].setFont(font);// set the font
                        }
                    }
                }

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        get[i][j] = tempGet[i][j];
                    }
                }

                if (isRunning) {
                    timer.cancel();
                    isRunning = false;
                }

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (get[i][j] == 0) {
                            textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                            // make values to be align center
                            textField[i][j].setFont(font); // set the font
                            textField[i][j].setText("");
                            continue;
                        } else {
                            textField[i][j].setText("" + get[i][j]);// add values into text field
                            textField[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                            // make values to be align center
                            textField[i][j].setFont(font);// set the font
                        }
                    }
                }

            }
        });

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);
    }
}
