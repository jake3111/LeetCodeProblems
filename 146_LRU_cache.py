from collections import OrderedDict

class LRUCache:

    capacity: int
    cache: OrderedDict

    def __init__(self, capacity: int):
        self.cache = OrderedDict()
        self.capacity = capacity
        

    def get(self, key: int) -> int:
        try:
            curr_val = self.cache[key]
            # Make the existing key the most recent
            del self.cache[key]
            self.cache[key] = curr_val
            return curr_val
        except:
            return -1
        

    def put(self, key: int, value: int) -> None:
        try:
            self.cache[key]
            # Make the existing key the most recent
            del self.cache[key]
        except:
            if len(self.cache) == self.capacity:
                # Remove the least recently used key
                del self.cache[next(iter(self.cache))]
        self.cache[key] = value


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)