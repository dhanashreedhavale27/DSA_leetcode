import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        // dp[j] stores the number of unique paths to reach column j in the current row
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] (new) = dp[j] (top cell from previous row) + dp[j-1] (left cell from current row)
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }
}