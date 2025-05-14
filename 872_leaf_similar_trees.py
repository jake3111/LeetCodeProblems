from collections import deque

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:

    def dfsLeaves(self, curr_leaf_queue: deque, curr_node: TreeNode):
        if curr_node.left is None and curr_node.right is None:
            curr_leaf_queue.append(curr_node)
        else:
            # Go in left, self, right order to find the leaves
            if curr_node.left is not None:
                self.dfsLeaves(curr_leaf_queue, curr_node.left)
            if curr_node.right is not None:
                self.dfsLeaves(curr_leaf_queue, curr_node.right)

    def leafSimilar(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        # Perform dfs on both trees and throw the leaves into queues.
        leaf_queue_1 = deque()
        self.dfsLeaves(leaf_queue_1, root1)
        leaf_queue_2 = deque()
        self.dfsLeaves(leaf_queue_2, root2)
        if len(leaf_queue_1) is not len(leaf_queue_2):
            return False
        while len(leaf_queue_1) > 0:
            if leaf_queue_1.popleft().val is not leaf_queue_2.popleft().val:
                return False
        return True