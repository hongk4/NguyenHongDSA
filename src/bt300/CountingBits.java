package bt300;

public class CountingBits {
    public static int[] countBits(int n) {
        int[] ans = new int[n+1];
        int sum,x = 0;
        for(int i = 0; i<=n ;i++){
            sum = 0;
            x = i;
            while(x!=0){
                sum += x%2;
                x=x/2;
            }
            ans[i] = sum;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ans = countBits(5);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]+" ");
        }
    }
}
