# This is Jake's submission

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:

    smallest_diff = 0
    curr_smallest_node = None

    def bst_value_search(self, root, target):
        curr_diff = abs(root.val - target)
        if curr_diff < .5:
            self.curr_smallest_node = root
            return
        if curr_diff == self.smallest_diff and root.val < self.curr_smallest_node.val:
            self.curr_smallest_node = root
        elif curr_diff < self.smallest_diff:
            self.smallest_diff = curr_diff
            self.curr_smallest_node = root
        if target < root.val and root.left:
            self.bst_value_search(root.left, target)
        elif target > root.val and root.right:
            self.bst_value_search(root.right, target)

    def closestValue(self, root: Optional[TreeNode], target: float) -> int:
        if root is None:
            return -1
        self.curr_smallest_node = root
        self.smallest_diff = abs(root.val - target)
        if self.smallest_diff < .5:
            return root.val
        if target < root.val and root.left:
            self.bst_value_search(root.left, target)
        elif target > root.val and root.right:
            self.bst_value_search(root.right, target)
        return self.curr_smallest_node.val
