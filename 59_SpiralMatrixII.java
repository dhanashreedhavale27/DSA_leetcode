class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int val = 1;

        while (top <= bottom && left <= right) {
            // 1. Fill Left to Right along the Top row
            for (int col = left; col <= right; col++) {
                matrix[top][col] = val++;
            }
            top++;

            // 2. Fill Top to Bottom along the Right column
            for (int row = top; row <= bottom; row++) {
                matrix[row][right] = val++;
            }
            right--;

            // 3. Fill Right to Left along the Bottom row
            for (int col = right; col >= left; col--) {
                matrix[bottom][col] = val++;
            }
            bottom--;

            // 4. Fill Bottom to Top along the Left column
            for (int row = bottom; row >= top; row--) {
                matrix[row][left] = val++;
            }
            left++;
        }

        return matrix;
    }
}