class Solution {
    public int distinctSubseqII(String s) {

        long MOD = 1000000007;

        long dp = 1;  // empty subsequence

        long[] last = new long[26];

        for (char ch : s.toCharArray()) {

            int index = ch - 'a';

            long newDp = (2 * dp) % MOD;

            // Remove duplicates caused by previous occurrence
            newDp = (newDp - last[index] + MOD) % MOD;

            // Store current dp before updating
            last[index] = dp;

            dp = newDp;
        }

        // Remove empty subsequence
        return (int)((dp - 1 + MOD) % MOD);
    }
}