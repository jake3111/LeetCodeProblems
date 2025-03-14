class Solution:
    def findMissingRanges(self, nums: List[int], lower: int, upper: int) -> List[List[int]]:
        result_list = []
        for local_num in nums:
            if lower == local_num:
                lower += 1
            elif lower < local_num:
                result_list.append((lower, local_num - 1))
                lower = local_num + 1
        if lower <= upper or len(nums) == 0:
            result_list.append((lower, upper))
        return result_list
