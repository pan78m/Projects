import java.util.Random;

public class SudokuGenerator {
    private static final int SIZE = 9;
    private static final int EMPTY_CELL = 0;

    private int[][] puzzle;

    public SudokuGenerator() {
        puzzle = new int[SIZE][SIZE];
    }

    public void generate() {
        // Generate a complete puzzle
        fillDiagonal();
        fillRemaining(0, 3);

        // Remove random cells to create a puzzle
        removeCells();
    }

    private void fillDiagonal() {
        for (int i = 0; i < SIZE; i = i + 3) {
            fillBox(i, i);
        }
    }

    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = getRandomNumber();
                } while (!isValid(row, col, num));

                puzzle[row + i][col + j] = num;
            }
        }
    }

    private int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9) + 1;
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (puzzle[row][i] == num || puzzle[i][col] == num) {
                return false;
            }
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean fillRemaining(int row, int col) {
        if (col >= SIZE && row < SIZE - 1) {
            row = row + 1;
            col = 0;
        }

        if (row >= SIZE && col >= SIZE) {
            return true;
        }

        if (row < 3) {
            if (col < 3) {
                col = 3;
            }
        } else if (row < SIZE - 3) {
            if (col == (int) (row / 3) * 3) {
                col = col + 3;
            }
        } else {
            if (col == SIZE - 3) {
                row = row + 1;
                col = 0;
                if (row >= SIZE) {
                    return true;
                }
            }
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isValid(row, col, num)) {
                puzzle[row][col] = num;
                if (fillRemaining(row, col + 1)) {
                    return true;
                }
                puzzle[row][col] = EMPTY_CELL;
            }
        }
        return false;
    }

    private void removeCells() {
        final int cellsToKeep = 50; // Adjust the number of cells to keep in the puzzle

        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (rand.nextInt(81) >= cellsToKeep) {
                    puzzle[i][j] = EMPTY_CELL;
                }
            }
        }
    }

    public int[][] getSudokuPuzzle() {
        generate();
        return puzzle;
    }

}