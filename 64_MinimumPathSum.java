class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        // Base case: starting cell
        dp[0] = grid[0][0];

        // Initialize first row
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        // Process remaining rows
        for (int i = 1; i < m; i++) {
            // First column of row i can only be reached from above
            dp[0] += grid[i][0];

            for (int j = 1; j < n; j++) {
                // dp[j] (new) = grid[i][j] + min(dp[j] (from top), dp[j - 1] (from left))
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }

        return dp[n - 1];
    }
}