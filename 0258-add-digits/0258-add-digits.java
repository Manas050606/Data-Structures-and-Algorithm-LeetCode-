class Solution {
    public int addDigits(int num) {
        if (num<=9){
            return num;
        }
        int sum = 0;

        while(num>0){
            int digit = num%10;
            sum+=digit;
            num/=10;
        }

        if (sum<=9){
            return sum;
        }
        else{
            return addDigits(sum);
        }
    }
}