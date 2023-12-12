package bt300;

public class Numberof1Bits {
    public static int hammingWeight(int n) {
        int sum = 0;
        for(int i = 0;i<=n; i++){
            if(n%10 == 1){
                sum++;
            }
            n=n/10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(111));
        System.out.println(1%10);
    }
}
