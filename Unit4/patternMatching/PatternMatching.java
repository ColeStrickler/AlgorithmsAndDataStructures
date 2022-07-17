


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class PatternMatching {

    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        Map<Character, Integer> last = buildLastTable(pattern);
        List<Integer> retList = new ArrayList<>();
        int i = 0;
        while (i <= (text.length() - pattern.length())) {
            int j = pattern.length() - 1;
            while (j >= 0 && comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0) {
                j = j - 1;
            }
            if (j == -1) {
                retList.add(i);
                i = i + 1;
            }
            else {
                int shift;
                try {
                    shift = last.get(text.charAt(i + j));
                } catch(Exception e) {
                    shift = -1;
                }
                
                if (shift < j) {
                    i = i + j - shift;
                }
                else {
                    i = i + 1;
                }
            }

        }
        return retList;
    }


    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        int m = pattern.length();
        Map<Character, Integer> last = new HashMap<>();
        for (int i = 0; i < m; i++) {
            last.put(pattern.charAt(i), i);
        } 
        return last;
    }
}