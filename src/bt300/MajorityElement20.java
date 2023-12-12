package bt300;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MajorityElement20 {
    public static int majorityElement(int[] nums) {
        int miles = nums.length/2;
        ArrayList<Integer> array = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            array.removeAll(Collections.singleton(nums[i]));
            if(array.size() <= miles){
                return nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }
}
