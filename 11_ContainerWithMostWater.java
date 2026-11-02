class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            // Calculate width and height of current container
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            
            // Calculate current water area and update maxWater
            int currentWater = width * currentHeight;
            maxWater = Math.max(maxWater, currentWater);

            // Move the pointer that points to the shorter line
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}