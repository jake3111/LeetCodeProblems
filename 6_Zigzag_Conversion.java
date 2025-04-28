class Solution {

    public void populateCharGrid(Queue<Character> [] charGrid, String s) {
        boolean goingDown = true;
        int queueIndex = 0;
        for (int i = 0;i < s.length();i++) {
            charGrid[queueIndex].add(s.charAt(i));
            if ((goingDown && queueIndex == charGrid.length - 1) ||
                (!goingDown && queueIndex == 0)) {
                goingDown = !goingDown;
            }
            if (goingDown && queueIndex != charGrid.length - 1) {
                queueIndex++;
            }
            if (!goingDown && queueIndex != 0) {
                queueIndex--;
            }
        }
    }

    public String convert(String s, int numRows) {
        // create an array of the size of numRows
        // that will represent the grid
        Queue<Character> [] charGrid = new Queue [numRows];
        for (int i = 0;i < numRows;i++) {
            charGrid[i] = new LinkedList<>();
        }

        // populate the grid with the characters of the string
        populateCharGrid(charGrid, s);

        char [] resCharArr = new char [s.length()];
        int k = 0;
        for (int i = 0;i < numRows;i++) {
            while (!charGrid[i].isEmpty()) {
                resCharArr[k] = charGrid[i].poll();
                k++;
            }
        }
        return new String(resCharArr);
    }
}