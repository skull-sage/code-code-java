package leetcode.range_query;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DuplicateIII {

    static class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
             for(int idx=0; idx < nums.length; idx++){
                int curr  = nums[idx]; 
                int jdx = (idx - indexDiff - 1);

                // as we move pass indexDiff, we check out of boundary entry
                if(jdx >= 0 && jdx == treeMap.get(nums[jdx])){
                    treeMap.remove(nums[jdx]);
                }

                System.out.println(treeMap);
                Integer start = curr - valueDiff;
                Integer end = curr + valueDiff; 
                
                Integer ciel = treeMap.ceilingKey(start);
                if (ciel != null && ciel >= start && ciel <= end )
                    return true;
                
                Integer floor = treeMap.floorKey(end);
                if (floor != null && floor >= start && floor <= end )
                    return true;
                
                treeMap.put(curr, idx);
            }

             return false;
        }
    }

    public static void main(String... args){
        int[] nums = {1,5,9,1,5,9};
        int indexDiff = 2;
        int valueDiff = 3;

        new Solution().containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff);
    }
    
}
