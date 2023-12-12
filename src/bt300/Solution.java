package bt300;

class Solution {
    public static boolean isPalindrome(String s1) {
        String s = s1.replaceAll("\\.","");
        String s2 = s.replaceAll(" ","");
        String s3 = s2.replaceAll(",","");
        String s4 = s3.replaceAll(":","").toLowerCase();
        for(int i = 0; i< s4.length() / 2 ; i++){
            String a1 = s4.substring(i, i + 1);
            String a2 =  s4.substring(s4.length() - i - 1, s4.length() - i);
                if (a1.equals(a2) == false) {
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "a.";
        System.out.println(isPalindrome(s));
    }
}
