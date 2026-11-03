import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Base case: formed a complete permutation
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip elements already used in the current permutation
            if (current.contains(nums[i])) {
                continue;
            }

            current.add(nums[i]); // Choose
            backtrack(nums, current, result); // Recurse
            current.remove(current.size() - 1); // Backtrack
        }
    }
}