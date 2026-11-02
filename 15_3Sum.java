import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Iterate through the array, fixing the first element 'i'
        for (int i = 0; i < nums.length - 2; i++) {
            // Early exit: since the array is sorted, if nums[i] > 0, 3 positive numbers can't sum to 0
            if (nums[i] > 0) break;

            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            // Step 3: Two-pointer search for the remaining two elements
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate values for the second element
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicate values for the third element
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    // Move pointers inward
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // Increase the sum by moving left pointer right
                } else {
                    right--; // Decrease the sum by moving right pointer left
                }
            }
        }

        return result;
    }
}