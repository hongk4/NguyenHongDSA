package bt300;

import java.util.*;

public class containsDuplicate {
    public static boolean kt(int[] a){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            if (set.contains(a[i])) {
                return true;
            }
            set.add(a[i]);
        }
        return false;
    }
    public static void main(String[] args) {
        int[] a = {1,2,3,1};
        System.out.println(kt(a));

    }
}
