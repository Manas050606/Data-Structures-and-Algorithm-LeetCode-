class Solution {
    public int reverse(int x) {
        long reversed = 0; // Use long to safely catch overflow
        
        while (x != 0) {
            int digit = x % 10;     // Extracts the last digit (handles negative numbers perfectly)
            reversed = (reversed * 10) + digit; // Appends the digit
            x /= 10;                // Removes the last digit
        }
        
        // Check if the reversed number fits within 32-bit integer boundaries
        if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int) reversed; // Safely cast back to int
    }
}
