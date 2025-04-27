class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int numValid = 0;
        int leftIndex = 0;
        int runningProduct = 1;
        for (int i = 0;i < nums.length;i++) {
            runningProduct *= nums[i];
            while (runningProduct >= k && leftIndex < i) {
                runningProduct /= nums[leftIndex];
                leftIndex++;
            }
            if (runningProduct < k) {
                numValid += (i - leftIndex) + 1;
            }
        }
        return numValid;
    }
}