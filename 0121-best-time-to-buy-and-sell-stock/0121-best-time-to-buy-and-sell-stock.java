class Solution {
    public int maxProfit(int[] a) {
        if (a == null || a.length == 0) return 0;
        
        int min = a[0];
        int maxProfit = 0;
        
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i]; // Found a cheaper day to buy for future days
            } else {
                // Check if selling today beats our best profit so far
                maxProfit = Math.max(maxProfit, a[i] - min);
            }
        }
        
        return maxProfit;
    }
}
