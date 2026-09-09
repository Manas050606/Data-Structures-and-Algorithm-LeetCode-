class Solution {
    public long countCommas(long n) {
        long answer = 0;
        long start = 1000;
        long commas = 1;

        while (start <= n) {
            long end = start * 1000 - 1;

            long count = Math.min(n, end) - start + 1;

            answer += count * commas;

            start *= 1000;
            commas++;
        }

        return answer;
    }
}