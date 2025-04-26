class Solution {
    public int longestOnes(int[] nums, int k) {
        int leftSide = 0;
        int maxLength = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                k--;
            }
            while (k < 0) {
                if (nums[leftSide] == 0) {
                    k++;
                }
                leftSide++;
            }
            i++;
            maxLength = Math.max(maxLength, i - leftSide);
        }
        return maxLength;
    }
}