class Solution {
    public String mergeAlternately(String word1, String word2) {
        // use 2 pointers across both strings and track them as you create the result
        char [] sbArr = new char [word1.length() + word2.length()];
        int i = 0;
        int k = 0;
        int z = 0;
        while (i < word1.length() && k < word2.length()) {
            sbArr[z++] = word1.charAt(i++);
            sbArr[z++] = word2.charAt(k++);
        }
        while (i < word1.length()) {
            sbArr[z++] = word1.charAt(i++);
        }
        while (k < word2.length()) {
            sbArr[z++] = word2.charAt(k++);
        }
        return new String(sbArr);
    }
}