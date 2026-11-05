class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[j] stores the min distance to convert word1 prefix to word2[0...j-1]
        int[] dp = new int[n + 1];

        // Base case: converting empty word1 to word2[0...j-1] requires j insertions
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            int prev = dp[0]; // Stores dp[i-1][j-1] (top-left element)
            dp[0] = i;        // Base case: converting word1[0...i-1] to empty word2 requires i deletions

            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store before updating dp[j] for next iteration

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev; // Characters match
                } else {
                    // 1 + min(Replace, Delete, Insert)
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j], dp[j - 1]));
                }

                prev = temp; // Update top-left for next column
            }
        }

        return dp[n];
    }
}