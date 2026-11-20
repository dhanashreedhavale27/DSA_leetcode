import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        int maxVal = nums[nums.length - 1];
        // dp[v] = max length of consecutive sequence ending with value v
        int[] dp = new int[maxVal + 2];
        int maxLen = 1;

        for (int x : nums) {
            // 1. Increment x -> x + 1 (must be computed before dp[x] is overwritten)
            dp[x + 1] = Math.max(dp[x + 1], dp[x] + 1);
            
            // 2. Keep x as x
            dp[x] = Math.max(dp[x], dp[x - 1] + 1);
            
            maxLen = Math.max(maxLen, Math.max(dp[x], dp[x + 1]));
        }

        return maxLen;
    }
}