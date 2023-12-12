package bt300;

import java.util.Map;
import java.util.HashMap;

public class RomanToInterger {
    public static int romanToInt1(String s) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        int count = 0;
        int[] ik = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (Integer key : map.keySet()) {
                if (s.substring(i, i + 1).equals(map.get(key))) {
                    ik[i] = key;
                }
            }
        }
        if (ik.length > 1) {
            for (int i = 0; i < ik.length - 1; i++) {
                if (ik[i + 1] > ik[i]) {
                    count += ik[i + 1] - ik[i];
                    i++;
                } else {
                    count += ik[i];
                }
            }
            if (ik[ik.length - 1] <= ik[ik.length - 2]) {
                count += ik[ik.length - 1];
            }
        } else {
            count += ik[ik.length - 1];
        }
        return count;
    }
    public static int romanToInt2(String s) {
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        int sum=0;
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        s=s.replace("IV","IIII");
        s=s.replace("IX","VIIII");
        s=s.replace("XL","XXXX");
        s=s.replace("XC","LXXXX");
        s=s.replace("CD","CCCC");
        s=s.replace("CM","DCCCC");
        for(int i=0;i<s.length();i++){
            sum = sum + (map.get(s.charAt(i)));
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt1("MCMXCIV"));
        System.out.print(romanToInt2("MCMXCIV"));
    }
}

