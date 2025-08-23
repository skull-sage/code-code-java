package leetcode;

import java.util.*;



public class KRemovals
{
   static class Solution {
        public int findLeastNumOfUniqueInts(int[] arr, int k) {
            Map<Integer, Integer> elmCountMap = new HashMap<>();
            int totalUnique = 0;

            for(int idx=0; idx<arr.length; idx++){
                Integer elm =  arr[idx];
                Integer count = elmCountMap.get(elm);
                if(count != null)
                    elmCountMap.put(elm,  count + 1);
                else elmCountMap.put(elm, 1);

            }

            Integer[] countList = elmCountMap.values().toArray(new Integer[0]);
            Arrays.sort(countList);

            int delCount = 0;
            int sumCount = 0;
            for(Integer c: countList)
            {
                if(sumCount + c <= k)
                {
                    sumCount += c;
                    delCount++;
                }
                else break;
            }

            return (countList.length - delCount);
        }
    }

    public static void main(String... args){

        int result = new Solution().findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2}, 3);

        System.out.println(result);
    }
}
