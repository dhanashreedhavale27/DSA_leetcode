class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                // Look for an empty cell
                if (board[r][c] == '.') {
                    // Try digits '1' through '9'
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, r, c, num)) {
                            board[r][c] = num; // Choose

                            // Recursively attempt to solve rest of the board
                            if (solve(board)) {
                                return true;
                            }

                            board[r][c] = '.'; // Backtrack (Un-choose)
                        }
                    }
                    // No valid number could be placed, trigger backtracking
                    return false;
                }
            }
        }
        return true; // Entire board filled successfully
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // Check row
            if (board[row][i] == c) return false;
            // Check column
            if (board[i][col] == c) return false;
            // Check 3x3 sub-box
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }
}