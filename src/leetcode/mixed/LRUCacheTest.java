package leetcode.mixed;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheTest
{
    static class LRUCache {

        LinkedHashMap<Integer, Integer> map;
        public LRUCache(int capacity) {
            // setting accessOrder true will make LinkedHashMap a LRU otherwise MRU
            map = new LinkedHashMap<>(capacity, 0.7f, true){
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            if(map.containsKey(key) == false)
                return -1;
            return map.get(key);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }
    }

    public static void main(String... args)
    {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.map);

        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.map);

        System.out.println("GET 1: "+lRUCache.get(1));    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
