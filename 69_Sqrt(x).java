class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        int left = 1;
        int right = x;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Equivalent to mid * mid <= x, but prevents integer overflow
            if (mid <= x / mid) {
                ans = mid;      // Found a candidate, try to find a larger one
                left = mid + 1;
            } else {
                right = mid - 1; // mid is too large
            }
        }

        return ans;
    }
}