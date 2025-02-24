public class 127_Word_Latter {
    public static boolean isOneTransform(String word1, String word2) {
        // we know both strings are the same length, so see if there's just one difference
        int numDifferences = 0;
        for (int i = 0;i < word1.length();i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                numDifferences++;
            }
            if (numDifferences > 1) {
                return false;
            }
        }
        // it's the next word and must be added to the queue and set
        return true;
    }

    public static int checkNextWord(Queue<String> bfsQueue,
        Set<String> usedWords,
        String endWord,
        List<String> wordList,
        int levelCount) {
        String queueString = bfsQueue.poll();
        if (queueString.equals(endWord)) {
            return levelCount;
        }
        for (String currString : wordList) {
            if (!usedWords.contains(currString) &&
                isOneTransform(queueString, currString)) {
                bfsQueue.add(currString);
                usedWords.add(currString);
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // use bfs and throw the used strings into the hashset.
        Queue<String> bfsQueue = new LinkedList<>();
        Set<String> usedWords = new HashSet<>();
        bfsQueue.add(beginWord);
        int levelCount = 0;
        while (!bfsQueue.isEmpty()) {
            levelCount++;
            int queueSize = bfsQueue.size();
            for (int i = 0;i < queueSize;i++) {
                int nextWordCheck =
                    checkNextWord(bfsQueue, usedWords, endWord, wordList, levelCount);
                if (nextWordCheck != -1) {
                    return nextWordCheck;
                }
            }
        }
        return 0;
    }
}
