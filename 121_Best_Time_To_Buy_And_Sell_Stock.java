class Solution {
    public int maxProfit(int[] prices) {
        // iterate it from the back and keep track of the max profit and highest peak
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int i = prices.length - 1;
        int maxProfit = 0;
        int maxPeak = prices[i--];
        while (i >= 0) {
            int currPeak = prices[i];
            int currProfit = maxPeak - currPeak;
            if (currProfit > maxProfit) {
                maxProfit = currProfit;
            }
            if (currPeak > maxPeak) {
                maxPeak = currPeak;
            }
            i--;
        }
        return maxProfit;
    }
}