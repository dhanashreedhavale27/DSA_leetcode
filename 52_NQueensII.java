class Solution {
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] mainDiag = new boolean[2 * n]; 
        boolean[] antiDiag = new boolean[2 * n]; 

        return backtrack(0, n, cols, mainDiag, antiDiag);
    }

    private int backtrack(int row, int n, boolean[] cols, boolean[] mainDiag, boolean[] antiDiag) {
        if (row == n) {
            return 1;
        }

        int count = 0;

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;     

            if (cols[col] || mainDiag[d1] || antiDiag[d2]) {
                continue;
            }

            cols[col] = true;
            mainDiag[d1] = true;
            antiDiag[d2] = true;

            count += backtrack(row + 1, n, cols, mainDiag, antiDiag);
            cols[col] = false;
            mainDiag[d1] = false;
            antiDiag[d2] = false;
        }

        return count;
    }
}