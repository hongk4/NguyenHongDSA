package bt300;

import java.util.Scanner;

public class snt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 30;
        for(int i = 0; i < n; i++){
            if(isPrime(i)&&kt(i)){
                System.out.println(i+" ");
            }
        }
    }
    public static boolean isPrime(int k) {
        int sum = 0;
        if (k==0){
            return false;
        }
        for (int i = 1; i <= k; i++) {
            if (k % i == 0) {
                sum++;
            }
        }
        return sum == 2;
    }
    public static boolean kt(int k){
        int sum = 0;
        String s = Integer.toString(k);
        if(k == 0){
            return false;
        }
        while(k != 0)
            if(isPrime(k%10)){
                k =k/10;
                sum++;
        }
        else
            return false;
        if(sum == s.length()){
            return true;
        }
        return false;
    }
}
