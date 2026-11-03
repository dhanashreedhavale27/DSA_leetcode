class Solution {
    public boolean canJump(int[] nums) {
        int maxReachable = 0;

        for (int i = 0; i < nums.length; i++) {
            // If the current index is beyond the farthest index we can reach
            if (i > maxReachable) {
                return false;
            }

            // Update the maximum index we can reach from here
            maxReachable = Math.max(maxReachable, i + nums[i]);

            // Early exit if the last index is already within reach
            if (maxReachable >= nums.length - 1) {
                return true;
            }
        }

        return maxReachable >= nums.length - 1;
    }
}