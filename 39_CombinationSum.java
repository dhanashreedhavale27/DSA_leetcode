import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> current, List<List<Integer>> result) {
        // Base Case 1: Target reached
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Base Case 2: Exceeded target
        if (remain < 0) {
            return;
        }

        // Explore choices starting from the current index to avoid duplicate combinations
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]); // Choose
            
            // Recurse with same index `i` because we can reuse the same element
            backtrack(candidates, remain - candidates[i], i, current, result); 
            
            current.remove(current.size() - 1); // Backtrack
        }
    }
}