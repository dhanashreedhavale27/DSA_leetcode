import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Sort candidates to easily handle duplicates and prune paths
        Arrays.sort(candidates);
        
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> current, List<List<Integer>> result) {
        // Base Case 1: Target reached
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Early Pruning: Since array is sorted, if current number exceeds remain, 
            // further numbers will also exceed remain.
            if (candidates[i] > remain) {
                break;
            }

            // Skip duplicate elements at the same tree depth/level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            current.add(candidates[i]); // Choose
            
            // Recurse with i + 1 (each element used at most once)
            backtrack(candidates, remain - candidates[i], i + 1, current, result);
            
            current.remove(current.size() - 1); // Backtrack
        }
    }
}