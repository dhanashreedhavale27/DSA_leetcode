class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Track seen numbers (1-9 mapped to indices 0-8)
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char current = board[r][c];

                // Skip empty cells
                if (current == '.') {
                    continue;
                }

                int num = current - '1'; // Convert '1'-'9' to index 0-8
                int boxIndex = (r / 3) * 3 + (c / 3);

                // If number is already seen in current row, column, or box
                if (rows[r][num] || cols[c][num] || boxes[boxIndex][num]) {
                    return false;
                }

                // Mark the number as seen
                rows[r][num] = true;
                cols[c][num] = true;
                boxes[boxIndex][num] = true;
            }
        }

        return true;
    }
}