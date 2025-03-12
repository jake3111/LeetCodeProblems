from collections import deque

class MovingAverage:

    curr_size = 0
    size = 0
    running_sum = 0
    moving_queue = deque()

    def __init__(self, size: int):
        self.size = size

    def next(self, val: int) -> float:
        if self.curr_size == self.size:
            self.running_sum -= self.moving_queue.popleft()
        else:
            self.curr_size += 1
        self.moving_queue.append(val)
        self.running_sum += val
        return self.running_sum / self.curr_size


# Your MovingAverage object will be instantiated and called as such:
# obj = MovingAverage(size)
# param_1 = obj.next(val)
