import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // Precompute proper divisors for all lengths from 2 to n
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d <= len / 2; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        // cost[i][j] = min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = Integer.MAX_VALUE;

                for (int d : divisors[len]) {
                    int changes = 0;
                    int groupLen = len / d;

                    // Check each of the d interleaved groups
                    for (int offset = 0; offset < d; offset++) {
                        int left = 0;
                        int right = groupLen - 1;
                        while (left < right) {
                            if (chars[i + offset + left * d] != chars[i + offset + right * d]) {
                                changes++;
                            }
                            left++;
                            right--;
                        }
                    }
                    minChanges = Math.min(minChanges, changes);
                }
                cost[i][j] = minChanges;
            }
        }

        // dp[p][j] = min changes to partition prefix of length j into p semi-palindromes
        int INF = 1_000_000;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            // Each semi-palindrome has length >= 2, so j >= 2 * p
            for (int j = 2 * p; j <= n; j++) {
                for (int i = 2 * (p - 1); i <= j - 2; i++) {
                    if (dp[p - 1][i] != INF) {
                        dp[p][j] = Math.min(dp[p][j], dp[p - 1][i] + cost[i][j - 1]);
                    }
                }
            }
        }

        return dp[k][n];
    }
}