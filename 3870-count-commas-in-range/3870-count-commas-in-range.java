class Solution {
    public int countCommas(int n) {
        String str = Integer.toString(n);
        int length = str.length();
        if(length<4){
            return 0;
        }
        return n - 1000 + 1;
    }
}