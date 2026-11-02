class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] represents if s[0...i-1] matches p[0...j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: empty string matches empty pattern
        dp[0][0] = true;

        // Base case: patterns like "a*", "a*b*", ".*" can match empty string
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pChar = p.charAt(j - 1);
                char sChar = s.charAt(i - 1);

                if (pChar == '*') {
                    // 1. Zero occurrences of the preceding element
                    boolean ignoreStar = dp[i][j - 2];
                    
                    // 2. One or more occurrences of the preceding element
                    char prevPChar = p.charAt(j - 2);
                    boolean matchPreceding = (prevPChar == '.' || prevPChar == sChar);
                    boolean useStar = matchPreceding && dp[i - 1][j];

                    dp[i][j] = ignoreStar || useStar;
                } else if (pChar == '.' || pChar == sChar) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}