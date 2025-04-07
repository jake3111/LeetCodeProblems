class Solution {

    static int leftIndex = 0;
    static int rightIndex = 0;
    static int uniqueSChars = 0;
    static int uniqueTChars = 0;
    static String resultString = null;

    public static void initVariables() {
        leftIndex = 0;
        rightIndex = 0;
        uniqueSChars = 0;
        uniqueTChars = 0;
        resultString = null;
    }

    public static void iterateLeftIndex(String s, int[] sFreq, int[] tFreq) {
        while (uniqueSChars == uniqueTChars) {
            int charIndex = (int)s.charAt(leftIndex) - (int)'A';
            sFreq[charIndex]--;
            if (sFreq[charIndex] < tFreq[charIndex]) {
                // it means it's a new character that should be added to the count. 
                uniqueSChars--;
            } else {
                leftIndex++;
            }
        }
    }

    public static void moveSlidingWindow(String s, int[] tFreq, int[] sFreq) {
        int charIndex = (int)s.charAt(rightIndex) - (int)'A';
        sFreq[charIndex]++;
        if (sFreq[charIndex] == tFreq[charIndex]) {
            // it means it's a new character that should be added to the count. 
            uniqueSChars++;
        }
        rightIndex++;
        if (uniqueSChars == uniqueTChars){
            // move the left index to find a candidate.
            iterateLeftIndex(s, sFreq, tFreq);
            String candidateString = s.substring(leftIndex, rightIndex);
            if (resultString == null || candidateString.length() < resultString.length()) {
                resultString = candidateString;
            }
            leftIndex++;
        }
    }

    public static void iterateTString(int i, String t, int[] tFreq) {
        int charIndex = (int)t.charAt(i) - (int)'A';
        tFreq[charIndex]++;
        if (tFreq[charIndex] == 1) {
            // it means it's a new character that should be added to the count. 
            uniqueTChars++;
        }
    }

    public static String minWindow(String s, String t) {
        // let's create arrays holding the char frequencies and the count of unique letters to meet up to.
        int [] tFreq = new int [59];
        int [] sFreq = new int [59];

        initVariables();

        // iterate the t string to fill in the freq variables.
        for (int i = 0;i < t.length();i++) {
            iterateTString(i, t, tFreq);
        }

        // keep track of the best string candidate and return it\
        // create right and left pointers in the s String and fill up the frequency array off that
        // start with the iteration of the right pointer. 
        while (rightIndex < s.length()) {
            moveSlidingWindow(s, tFreq, sFreq);
        }
        return resultString != null ? resultString : "";
    }
}