from collections import deque

class Solution:

    def fillStacks(self, i: int, s: str, open_spaces: deque, closed_spaces: deque):
        if s[i] == '(':
            open_spaces.append(i)
        elif s[i] == ')':
            if len(open_spaces) > 0:
                open_spaces.pop()
            else:
                closed_spaces.append(i)

    def minRemoveToMakeValid(self, s: str) -> str:
        # Create two stacks to keep hold of excess parenthesis indices
        open_spaces = deque()
        closed_spaces = deque()
        for i in range(len(s)):
            self.fillStacks(i, s, open_spaces, closed_spaces)
        
        c_arr = ['\0'] * (len(s) - len(open_spaces) - len(closed_spaces))
        i = len(s) - 1
        k = len(c_arr) - 1
        while i >= 0:
            curr_char = s[i]
            if curr_char == '(' and open_spaces and open_spaces[-1] == i:
                open_spaces.pop()
            elif curr_char == ')' and closed_spaces and closed_spaces[-1] == i:
                closed_spaces.pop()
            else:
                c_arr[k] = curr_char
                k -= 1
            i -= 1
        return ''.join(c_arr)