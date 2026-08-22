public class Solution {
    public boolean checkDivisibility(int n) {
        boolean res = false;
        int org=n;
        int sum=0;
        int product=1;
        while(n>0){
            int digit=n%10;
            sum+=digit;
            product*=digit;
            n/=10;
        }
        if(org%(sum+product)==0){
            res=true;
        }
        return res;
    }
}