class Solution {

    // Create a LinkedList holding the resulting list of strings.
    List<String> resultStrings = new LinkedList<>();

    // Create a StringBuilder that parses together the results to put onto the resulting list.
    StringBuilder sb = new StringBuilder();

    // The blocks can span multiple strings, so there will need to be a tracker across the loop of all the strings. 
    boolean isCommentBlock = false;

    public int startComment(String currString, int i) {
        if (currString.charAt(i + 1) == '/') {
            // setting this to the end of the string to start a new line
            i = currString.length() - 1;
        } else if (currString.charAt(i + 1) == '*') {
            isCommentBlock = true;
            i++;
        } else {
            sb.append(currString.charAt(i));
        }
        return i;
    }

    // returns the updated i value given the current string and current index value. 
    public int analyzeString(String currString, int i) {
        // check if it's the end of a comment block
        if (isCommentBlock &&
            i < currString.length() - 1 &&
            currString.charAt(i) == '*' &&
            currString.charAt(i + 1) == '/') {
            isCommentBlock = false;
            i++;
        } else if (!isCommentBlock) {
            // checking if it should start a comment. 
            if (i < currString.length() - 1 && currString.charAt(i) == '/') {
                // one base case I want to get out of the way is "//" <- because that ends the line and moves it to a new one.
                i = startComment(currString, i);
            } else {
                sb.append(currString.charAt(i));
            }
        }
        return i;
    }

    public List<String> removeComments(String[] source) {
        
        // Given this, we will iterate the array of strings to parse out final characters and then add that onto the StringBuilder.
        for (String currString : source) {
            // iterate the current string at hand and append the final characters onto sb if they are not part of a comment
            for (int i = 0; i < currString.length();i++) {
                // let's cover if it is a block comment and it should be ignored unless it is the end of the block comment. 
                i = analyzeString(currString, i);
            }
            if (!isCommentBlock && sb.length() > 0) {
                resultStrings.add(sb.toString());
                sb = new StringBuilder();
            }
        }

        return resultStrings;
    }
}