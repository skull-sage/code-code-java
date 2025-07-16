package leetcode.arrays;

import java.util.*;
import java.util.function.Consumer;

public class ThreeSum {
    static class Solution
    {
        public void calcSum(int[] nums, int idx, int sum, List<Integer> triplet, Consumer<List<Integer>> acceptor)
        {
            if(triplet.size() == 3)
            {
                if(sum == 0)
                    acceptor.accept(new ArrayList<>(triplet));

                return;
            }

            if(idx >= nums.length)
                return;



            triplet.add(nums[idx]);
            calcSum(nums, idx+1, sum+nums[idx], triplet, acceptor);
            triplet.remove(triplet.size()-1);
            calcSum(nums, idx+1, sum, triplet, acceptor);

        }

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
                    }

                    if(j > idx + 1 && b == nums[j - 1])
                        continue;
                    if(k < nums.length - 1 && c == nums[k + 1])
                        continue;


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
