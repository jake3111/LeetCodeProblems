from collections import deque

class Solution:

    has_cycle = False

    def processPrereq(self, prereq_map, visited, i, res_stack, dfs_set):
        if i in dfs_set:
            self.has_cycle = True
            return
        if visited[i] is False:
            dfs_set[i] = True
            self.dfsPrereqGraph(prereq_map, visited, i, res_stack, dfs_set)
            del dfs_set[i]
            if self.has_cycle:
                return

    def dfsPrereqGraph(self, prereq_map, visited, i, res_stack, dfs_set):
        visited[i] = True
        prereq_list = []
        if i in prereq_map:
            prereq_list = prereq_map[i]
        for local_prereq in prereq_list:
            self.processPrereq(prereq_map, visited, local_prereq, res_stack, dfs_set)
            if self.has_cycle:
                return
        res_stack.append(i)

    def iterateCourses(self, prereq_map, visited, i, res_stack, dfs_set):
        # Check the dependencies and dfs them if need be
        if not visited[i]:
            dfs_set[i] = True
            self.dfsPrereqGraph(prereq_map, visited, i, res_stack, dfs_set)
            del dfs_set[i]
            if self.has_cycle:
                return []

    def findOrder(self, numCourses: int, prerequisites):
        visited = [False] * numCourses
        res_stack = deque()
        prereq_map = {}
        dfs_set = {}

        for local_prereq in prerequisites:
            if local_prereq[0] not in prereq_map:
                prereq_map[local_prereq[0]] = []
            prereq_map[local_prereq[0]].append(local_prereq[1])

        for i in range(numCourses):
            self.iterateCourses(prereq_map, visited, i, res_stack, dfs_set)
        if self.has_cycle:
            return []

        # create a res array from the contents of the stack
        res_arr = [0] * numCourses
        for i in range(numCourses - 1, -1, -1):
            res_arr[i] = res_stack.pop()
        return res_arr
