class Solution {
    public String convert(String s, int numRows) {
        // Base case: if numRows is 1 or string length is less than numRows, output is identical
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Create a StringBuilder for each row
        StringBuilder[] rows = new StringBuilder[Math.min(numRows, s.length())];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }

        int currRow = 0;
        boolean goingDown = false;

        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            rows[currRow].append(c);

            // Reverse direction if we hit the top or bottom row
            if (currRow == 0 || currRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // Move up or down based on current direction
            currRow += goingDown ? 1 : -1;
        }

        // Combine all rows into a single string
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }
}