import java.util.Arrays;
import java.util.TreeMap;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        // Step 1: Compute the cost for every window of size x using Two TreeMaps (Multiset)
        WindowMedianTracker tracker = new WindowMedianTracker();

        for (int i = 0; i < n; i++) {
            tracker.add(nums[i]);
            if (i >= x) {
                tracker.remove(nums[i - x]);
            }
            if (i >= x - 1) {
                cost[i - x + 1] = tracker.getCost();
            }
        }

        // Step 2: Dynamic Programming to pick k non-overlapping windows
        // dp[j][i] = min cost to select j windows using elements up to index i (1-indexed for dp array)
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], Long.MAX_VALUE / 2); // Avoid overflow during addition
        }

        for (int j = 1; j <= k; j++) {
            for (int i = j * x; i <= n; i++) {
                // Option 1: Don't take a window ending at i - 1
                dp[j][i] = dp[j][i - 1];

                // Option 2: Take a window ending at i - 1 (starts at i - x)
                long currentCost = cost[i - x];
                if (dp[j - 1][i - x] != Long.MAX_VALUE / 2) {
                    dp[j][i] = Math.min(dp[j][i], dp[j - 1][i - x] + currentCost);
                }
            }
        }

        return dp[k][n];
    }

    // Helper class to maintain two dynamic halves of a sliding window
    private static class WindowMedianTracker {
        private final TreeMap<Integer, Integer> left = new TreeMap<>();  // Max-heap side
        private final TreeMap<Integer, Integer> right = new TreeMap<>(); // Min-heap side
        private int leftSize = 0;
        private int rightSize = 0;
        private long leftSum = 0;
        private long rightSum = 0;

        public void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                addMap(left, val);
                leftSize++;
                leftSum += val;
            } else {
                addMap(right, val);
                rightSize++;
                rightSum += val;
            }
            rebalance();
        }

        public void remove(int val) {
            if (left.containsKey(val)) {
                removeMap(left, val);
                leftSize--;
                leftSum -= val;
            } else {
                removeMap(right, val);
                rightSize--;
                rightSum -= val;
            }
            rebalance();
        }

        public long getCost() {
            long median = left.lastKey();
            // Total operations = sum of (median - left_elements) + sum of (right_elements - median)
            return (median * leftSize - leftSum) + (rightSum - median * rightSize);
        }

        private void rebalance() {
            while (leftSize > rightSize + 1) {
                int maxLeft = left.lastKey();
                removeMap(left, maxLeft);
                leftSize--;
                leftSum -= maxLeft;

                addMap(right, maxLeft);
                rightSize++;
                rightSum += maxLeft;
            }
            while (leftSize < rightSize) {
                int minRight = right.firstKey();
                removeMap(right, minRight);
                rightSize--;
                rightSum -= minRight;

                addMap(left, minRight);
                leftSize++;
                leftSum += minRight;
            }
        }

        private void addMap(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        private void removeMap(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }
    }
}