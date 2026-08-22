class Solution {
    public int totalMoney(int n) {
        int total = 0;
        int monday = 1;
        int day = 1;

        for (int i = 1; i <= n; i++) {
            total += monday + day - 1;

            day++;

            // Start of a new week
            if (day == 8) {
                day = 1;
                monday++;
            }
        }

        return total;
    }
}