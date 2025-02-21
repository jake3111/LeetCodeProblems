class Solution {
    public int maxProfit(int[] prices) {
        // iterate from the back and add to the running sum anytime there's a profit
        if (prices == null | prices.length == 0) {
            return 0;
        }
        int i = prices.length - 1;
        int currPeak = prices[i--];
        int sumProfit = 0;
        while (i >= 0) {
            int currPrice = prices[i];
            if (currPrice < currPeak) {
                sumProfit += currPeak - currPrice;
            }
            currPeak = currPrice;
            i--;
        }
        return sumProfit;
    }
}