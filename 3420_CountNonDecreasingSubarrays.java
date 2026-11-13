import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long totalSubarrays = 0;
        long currentCost = 0;
        int r = n - 1;

        // Stores indices of active prefix maxima in [l, r] in decreasing order of value
        Deque<Integer> deque = new ArrayDeque<>();

        for (int l = n - 1; l >= 0; l--) {
            // 1. Expand window to the left by including nums[l]
            while (!deque.isEmpty() && nums[deque.peekFirst()] <= nums[l]) {
                int overriddenIdx = deque.pollFirst();
                int nextBoundary = deque.isEmpty() ? r + 1 : deque.peekFirst();
                currentCost += (long) (nums[l] - nums[overriddenIdx]) * (nextBoundary - overriddenIdx);
            }
            deque.offerFirst(l);

            // 2. Shrink window from the right until cost <= k
            while (currentCost > k) {
                int governingMaxIdx = deque.peekLast();
                currentCost -= (long) (nums[governingMaxIdx] - nums[r]);
                if (governingMaxIdx == r) {
                    deque.pollLast();
                }
                r--;
            }

            // 3. All subarrays starting at l and ending in [l, r] are valid
            totalSubarrays += (r - l + 1);
        }

        return totalSubarrays;
    }
}