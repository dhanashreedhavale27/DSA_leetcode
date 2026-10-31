class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        int[][][][] target = new int[m][n][2][2];

        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                
                int nr = r, nc = c + 1;
                if (nc < n && grid[nr][nc] == 1) {
                    
                    target[r][c][0] = target[nr][nc][1];
                } else {
                    target[r][c][0] = new int[]{nr, nc};
                }

                nr = r + 1; nc = c;
                if (nr < m && grid[nr][nc] == 1) {
                    target[r][c][1] = target[nr][nc][0];
                } else {
                    target[r][c][1] = new int[]{nr, nc};
                }
            }
        }

        int[][][] dp = new int[m][n][2];

        dp[0][0][0] = 1;
        dp[0][0][1] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) {
                
                    int[] rightDest = target[0][0][0];
                    if (rightDest[0] < m && rightDest[1] < n) {
                        dp[rightDest[0]][rightDest[1]][0] = (dp[rightDest[0]][rightDest[1]][0] + 1) % MOD;
                    }
                    
                    int[] downDest = target[0][0][1];
                    if (downDest[0] < m && downDest[1] < n) {
                        dp[downDest[0]][downDest[1]][1] = (dp[downDest[0]][downDest[1]][1] + 1) % MOD;
                    }
                    continue;
                }

                int totalWaysAtCell = (dp[r][c][0] + dp[r][c][1]) % MOD;
                if (totalWaysAtCell == 0 || grid[r][c] == 1) continue;

                int[] rightDest = target[r][c][0];
                if (rightDest[0] < m && rightDest[1] < n) {
                    dp[rightDest[0]][rightDest[1]][0] = (dp[rightDest[0]][rightDest[1]][0] + totalWaysAtCell) % MOD;
                }

                int[] downDest = target[r][c][1];
                if (downDest[0] < m && downDest[1] < n) {
                    dp[downDest[0]][downDest[1]][1] = (dp[downDest[0]][downDest[1]][1] + totalWaysAtCell) % MOD;
                }
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}