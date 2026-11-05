class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Traverse from rightmost (least significant) digit to left
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // No further carry needed
            }
            // If digit is 9, it becomes 0 and carry continues
            digits[i] = 0;
        }

        // If all digits were 9 (e.g., 999 -> 1000)
        int[] result = new int[n + 1];
        result[0] = 1; // Default values for rest of elements in Java are 0
        return result;
    }
}