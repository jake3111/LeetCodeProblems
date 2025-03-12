from collections import deque

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):

    min_column = 0
    max_column = 0

    def levelOrderTraversal(self, level_order_queue, column_map):
        queue_size = len(level_order_queue)
        for i in range(queue_size):
            local_tuple = level_order_queue.popleft()
            if local_tuple[1] < self.min_column:
                self.min_column = local_tuple[1]
            if local_tuple[1] > self.max_column:
                self.max_column = local_tuple[1]
            if column_map.get(local_tuple[1]) is None:
                column_map[local_tuple[1]] = []
            column_map[local_tuple[1]].append(local_tuple[0].val)
            if local_tuple[0].left != None: 
                level_order_queue.append((local_tuple[0].left, local_tuple[1] - 1))
            if local_tuple[0].right != None:
                level_order_queue.append((local_tuple[0].right, local_tuple[1] + 1))


    def verticalOrder(self, root):
        """
        :type root: Optional[TreeNode]
        :rtype: List[List[int]]
        """
        if root is None: 
            return []
        column_map = {}
        level_order_queue = deque()
        level_order_queue.append((root, 0))
        # The level order queue has the node and the column index too. 
        while level_order_queue:
            self.levelOrderTraversal(level_order_queue, column_map)
        return_list: List = [False] * (self.max_column - self.min_column + 1)
        # i: int = self.min_column
        i : int = 0
        while i < len(return_list):
            return_list[i] = column_map[self.min_column]
            i += 1
            self.min_column += 1
        return return_list
