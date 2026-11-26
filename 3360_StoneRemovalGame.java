class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean isAliceTurn = true;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            isAliceTurn = !isAliceTurn;
        }

        // The player whose turn it is now cannot move and loses.
        // Alice wins if it is Bob's turn (!isAliceTurn).
        return !isAliceTurn;
    }
}