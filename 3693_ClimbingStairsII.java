class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] represents minimum cost to reach step i
        int[] dp = new int[n + 1];
        dp[0] = 0; // Base case: starting at step 0 costs 0

        for (int j = 1; j <= n; j++) {
            int minCost = Integer.MAX_VALUE;

            // Try jumps of size 1, 2, and 3 steps backwards
            for (int k = 1; k <= 3 && j - k >= 0; k++) {
                int jumpCost = costs[j - 1] + k * k; // cost = costs[j] + (j - i)^2
                minCost = Math.min(minCost, dp[j - k] + jumpCost);
            }

            dp[j] = minCost;
        }

        return dp[n];
    }
}