import java.util.ArrayList;
import java.util.List;

class Solution {
    // Mapping digits 0-9 to their corresponding keypad letters
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // Base case: empty input returns an empty list
        if (digits == null || digits.length() == 0) {
            return result;
        }

        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder currentPath, List<String> result) {
        // Base case: formed a valid combination of full length
        if (index == digits.length()) {
            result.add(currentPath.toString());
            return;
        }

        // Get letters corresponding to the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];

        for (char c : letters.toCharArray()) {
            currentPath.append(c);                                 // Choose
            backtrack(digits, index + 1, currentPath, result);     // Explore next digit
            currentPath.deleteCharAt(currentPath.length() - 1);    // Un-choose (Backtrack)
        }
    }
}