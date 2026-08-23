class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        // First half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        // Second half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        // Odd number of '?' means Alice can always win
        if ((leftQ + rightQ) % 2 != 0) {
            return true;
        }

        // Otherwise, Bob wins only when this exact equality holds
        return leftSum - rightSum != 9 * (rightQ - leftQ) / 2;
    }
}