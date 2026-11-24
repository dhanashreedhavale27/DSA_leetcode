class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            long currentGcd = 0;
            long currentLcm = 1;

            for (int j = i; j < n; j++) {
                long val = nums[j];
                prod *= val;
                currentGcd = gcd(currentGcd, val);
                currentLcm = lcm(currentLcm, val);

                // Since nums[k] <= 10, lcm * gcd is bounded by 2520 * 10 = 25200.
                // Breaking early prevents 64-bit integer overflow on prod.
                if (prod > currentLcm * currentGcd) {
                    break;
                }

                if (prod == currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}