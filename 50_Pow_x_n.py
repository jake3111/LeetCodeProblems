class Solution:

    def subPow(self, x: float, n: int) -> float:
        if n == 1:
            return x
        if n % 2 == 1:
            return self.subPow(x, n - 1) * x
        result = self.subPow(x, n // 2)
        return result * result

    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1
        if n < 0:
            return self.subPow(1 / x, n * -1)
        if n == 1:
            return x
        if n % 2 == 1:
            return self.subPow(x, n - 1) * x
        result = self.subPow(x, n // 2)
        return result * result
