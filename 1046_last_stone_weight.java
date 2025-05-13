class Solution {
    public int lastStoneWeight(int[] stones) {
        // throw the stones into a prioirty queue and then operate accordingly.
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.add(stone);
        }
        while (maxHeap.size() > 1) {
            // pop the top 2 off and the add the abs difference
            maxHeap.add(Math.abs(maxHeap.poll() - maxHeap.poll()));
        }
        return maxHeap.peek();
    }
}