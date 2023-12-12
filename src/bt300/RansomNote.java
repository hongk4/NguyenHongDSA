package bt300;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Integer, Character> mapran = new HashMap<Integer, Character>();
        Map<Integer, Character> mapmag = new HashMap<Integer, Character>();
        for(int i = 0;i < ransomNote.length();i++){
            mapran.put(i,ransomNote.charAt(i));
        }
        for(int j = 0;j < magazine.length();j++){
            mapmag.put(j,magazine.charAt(j));
        }
        int cout = ransomNote.length();
        for(int i = 0;i < ransomNote.length();i++){
            for(int j = 0;j < magazine.length();j++){
                if(mapran.get(i) == mapmag.get(j) && mapran.get(i) != null){
                    mapran.remove(i);
                    mapmag.remove(j);
                    cout--;
                }
            }
        }
        if(cout == 0){
            return true;
        }
        else
            return false;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("aab","baa"));

    }
}
