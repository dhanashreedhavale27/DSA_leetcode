import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        
        // Track occupied columns and diagonals for O(1) checks
        boolean[] cols = new boolean[n];
        boolean[] mainDiag = new boolean[2 * n]; // row - col + n
        boolean[] antiDiag = new boolean[2 * n]; // row + col

        // Initialize empty chessboard with '.'
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        backtrack(0, n, board, cols, mainDiag, antiDiag, result);
        return result;
    }

    private void backtrack(int row, int n, char[][] board, boolean[] cols, 
                           boolean[] mainDiag, boolean[] antiDiag, List<List<String>> result) {
        // Base case: All queens placed successfully
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n; // Main diagonal index
            int d2 = row + col;     // Anti-diagonal index

            // Skip invalid positions
            if (cols[col] || mainDiag[d1] || antiDiag[d2]) {
                continue;
            }

            // Place Queen & mark constraints
            board[row][col] = 'Q';
            cols[col] = true;
            mainDiag[d1] = true;
            antiDiag[d2] = true;

            // Recurse to next row
            backtrack(row + 1, n, board, cols, mainDiag, antiDiag, result);

            // Backtrack
            board[row][col] = '.';
            cols[col] = false;
            mainDiag[d1] = false;
            antiDiag[d2] = false;
        }
    }

    private List<String> constructBoard(char[][] board) {
        List<String> path = new ArrayList<>();
        for (char[] row : board) {
            path.add(new String(row));
        }
        return path;
    }
}