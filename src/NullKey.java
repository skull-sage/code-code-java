import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;


public class NullKey {
    public static void main(String... args){

        ConcurrentHashMap cMap = new ConcurrentHashMap();
        cMap.put(null, "hukka hua");
        Comparator<Integer> nullAllowingComparator = Comparator.nullsFirst(Comparator.naturalOrder());
        NavigableMap<Integer, String> navigableMap =  new TreeMap<>(nullAllowingComparator);
        navigableMap.put(null, "Unpleasant Null");
        navigableMap.put(50, "This is 50");
        navigableMap.put(20, "this is 20");
        System.out.println(navigableMap);
        //prints nulls_first: {null=Unpleasant Null, 20=this is 20, 50=This is 50}
    }
}
