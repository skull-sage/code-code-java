package leetcode.arrays;

import java.util.*;
import java.util.function.Consumer;

public class ThreeSum {
    static class Solution
    {
        

        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> tripletList = new ArrayList<>();

            Arrays.sort(nums);
            //Set<Integer> firstSet = new HashSet<>();

            for(int idx=0 ; idx < nums.length; idx++)
            {

                Integer a = nums[idx];

                if(idx > 0 && a == nums[idx - 1])
                    continue;

                int j = idx + 1;
                int k = nums.length - 1;
                while(j < k)
                {
                    Integer b = nums[j];
                    Integer c = nums[k];
                    Integer sum = a + b + c;

                    if(sum == 0)
                    {
                        tripletList.add(Arrays.asList(a, b, c));
                        break;
                    }

                    while (sum < 0 && b == nums[j] && j < k) {
                        j++;
                    }
                    
                    while (sum > 0 && c == nums[k] && j < k){
                        k--;
                    }


                }
            }

            return tripletList;
        }
    }

    public static void main(String... args)
    {
        int[] nums = {0, 0, 0, 0}; //{-1,0,1,2,-1,-4};
        Object result = new Solution().threeSum(nums);
        System.out.println(result);
    }

}
