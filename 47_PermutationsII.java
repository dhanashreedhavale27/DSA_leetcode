import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Sort to group duplicate numbers together
        Arrays.sort(nums);
        
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // Base case: formed a full permutation
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if already used in the current branch
            if (used[i]) {
                continue;
            }

            // Skip duplicate elements to avoid redundant branches
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            current.add(nums[i]); // Choose

            backtrack(nums, used, current, result); // Recurse

            current.remove(current.size() - 1); // Backtrack
            used[i] = false;
        }
    }
}