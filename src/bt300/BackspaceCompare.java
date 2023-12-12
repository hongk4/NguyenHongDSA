package bt300;

import java.util.LinkedList;
import java.util.List;

public class BackspaceCompare {
    public static boolean backspaceCompare(String s, String t) {

        StringBuilder t1 = new StringBuilder(s);
        for (int i = t1.length() - 1; i >= 0; i--) {
            if (t1.lastIndexOf("#") == 0 || t1.indexOf("#") == -1) {
                break;
            }
            if (t1.charAt(i) == '#' && t1.charAt(i - 1) != '#') {
                t1.delete(i, i + 1);
                t1.delete(i - 1, i);
                i=t1.length();
            }

        }
        StringBuilder t2 = new StringBuilder(t);
        for (int i = t2.length() - 1; i >= 0; i--) {
            if (t2.lastIndexOf("#") == 0 || t2.indexOf("#") == -1) {
                break;
            }
            if (t2.charAt(i) == '#' && t2.charAt(i - 1) != '#') {
                t2.delete(i, i + 1);
                t2.delete(i - 1, i);
                i=t2.length();
            }

        }
        String a1 = String.valueOf(t1);
        String a2 = String.valueOf(t2);
        return a1.equals(a2);
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("y#fo##f","y#f#o##f"));
    }
}
