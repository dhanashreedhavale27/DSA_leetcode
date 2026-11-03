class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        // Loop up to nums.length - 1 because when we reach the last index, we're done
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // If we reached the end of the range for the current jump
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // Early exit if the last index is already reachable
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}