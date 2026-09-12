import java.util.*;

class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // [left, right, weight, original index]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by ending position
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        // prev[i] = last interval whose end < current interval's start
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = findPrevious(arr, i);
        }

        /*
         * dp[i][k]:
         * Best answer using first i intervals
         * with at most k intervals selected.
         */
        State[][] dp = new State[n + 1][5];

        /*
         * IMPORTANT:
         * Initialize EVERY state so nothing is null.
         */
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new int[0]);
            }
        }

        // DP
        for (int i = 1; i <= n; i++) {

            int current = i - 1;

            for (int k = 1; k <= 4; k++) {

                // Option 1: Skip current interval
                State skip = dp[i - 1][k];

                // Option 2: Take current interval
                State previous = dp[prev[current] + 1][k - 1];

                int[] newIndices = Arrays.copyOf(
                    previous.indices,
                    previous.indices.length + 1
                );

                newIndices[newIndices.length - 1] = arr[current][3];

                // Indices must be sorted for lexicographical comparison
                Arrays.sort(newIndices);

                State take = new State(
                    previous.score + arr[current][2],
                    newIndices
                );

                dp[i][k] = better(take, skip) ? take : skip;
            }
        }

        return dp[n][4].indices;
    }

    /*
     * Find the last interval j < i such that:
     *
     * arr[j].right < arr[i].left
     */
    private int findPrevious(int[][] arr, int i) {

        int left = 0;
        int right = i - 1;
        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid][1] < arr[i][0]) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    /*
     * Returns true if a is better than b.
     */
    private boolean better(State a, State b) {

        // Higher score is better
        if (a.score != b.score) {
            return a.score > b.score;
        }

        // Same score -> lexicographically smaller indices
        int len = Math.min(a.indices.length, b.indices.length);

        for (int i = 0; i < len; i++) {

            if (a.indices[i] != b.indices[i]) {
                return a.indices[i] < b.indices[i];
            }
        }

        // If one is a prefix of the other,
        // shorter array is lexicographically smaller.
        return a.indices.length < b.indices.length;
    }

    static class State {

        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }
}