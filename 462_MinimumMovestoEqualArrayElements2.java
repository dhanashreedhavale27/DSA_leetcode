import java.util.Arrays;
class Solution {
    public int minMoves2(int[] nums) {
        // Step 1: Sort the array to easily find the median
        Arrays.sort(nums);

        // Step 2: The median element
        int median = nums[nums.length / 2];

        // Step 3: Sum the absolute differences from each element to the median
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}