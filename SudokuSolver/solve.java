package mintern;

import java.util.Random;

public class SimpleSudokuSolver {

    private static final int size = 9;
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[][] board = generatePuzzle();

        System.out.println("Generated Sudoku Puzzle:");
        printBoard(board);

        if (solve(board)) {
            System.out.println("Sudoku solved successfully!");
        } else {
            System.out.println("Unsolvable board :(");
        }

        System.out.println("Final Sudoku Board:");
        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int i = 0; i < size; i++) {
            if (board[row][i] == num || board[i][col] == num || 
                board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean solve(int[][] board) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= size; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] generatePuzzle() {
        int[][] board = new int[size][size];
        boolean solvable = random.nextBoolean();

        if (solvable) {
            solve(board);
            for (int i = 0; i < 40; i++) {
                int row = random.nextInt(size);
                int col = random.nextInt(size);
                board[row][col] = 0;
            }
        } else {
            for (int i = 0; i < 20; i++) {
                int row = random.nextInt(size);
                int col = random.nextInt(size);
                int num = random.nextInt(size) + 1;
                if (isSafe(board, row, col, num)) {
                    board[row][col] = num;
                }
            }
        }

        return board;
    }
}
