class Solution {
    public int divide(int dividend, int divisor) {
        // Edge case: overflow when dividing Integer.MIN_VALUE by -1
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine if the result will be negative
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert to long and take absolute values to avoid overflow during operations
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int result = 0;

        // Perform binary long division using bit shifts
        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            int count = 0;

            // Double tempDivisor until it's greater than absDividend
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                count++;
            }

            // Subtract the largest shifted divisor found
            absDividend -= tempDivisor;

            // Add corresponding power of 2 to the result
            result += (1 << count);
        }

        return isNegative ? -result : result;
    }
}