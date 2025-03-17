class Solution:
# Perform binary search to find a peak on the array
    def findPeakElement(self, nums: List[int]) -> int:
        left_edge = 0
        right_edge = len(nums) - 1
        if right_edge == 1:
            return 0 if nums[0] > nums[1] else 1
        while left_edge < right_edge:
            median = int((left_edge + right_edge) / 2)
            if nums[median] > nums[median + 1] and nums[median] > nums[median - 1]:
                return median
            if nums[median] < nums[median + 1] and nums[median] > nums[median - 1]:
                # It's increasing on the right
                left_edge = median + 1
            elif nums[median] > nums[median + 1] and nums[median] < nums[median - 1]:
                # It's increasing on the left
                right_edge = median - 1
            elif median == 0 and nums[median] < nums[median + 1]:
                return 1
            elif median == len(nums) - 1 and nums[median] > nums[median - 1]:
                return len(nums) - 1
            else:
                right_edge = median
        return left_edge