package leetcode.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NextBigPermutation
{

    private static void swap(int[] nums, int i, int j)
    {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void nextPermutation(int[] nums)
    {
         int smIdx = -1;
         for(int idx = nums.length-1; idx>=0; idx--)
         {
             if(nums[idx-1] < nums[idx])
             {
                 smIdx = idx - 1;
                 break;
             }


         }

         if(smIdx >= 0)
         {
             int smPivot = nums[smIdx];
             int lgIdx = nums.length - 1;

             while (nums[lgIdx] <= nums[smIdx])
                lgIdx--;

             swap(nums, smIdx, lgIdx);

         }

         int i = smIdx + 1;
         int j = nums.length - 1;
         while(i < j)
         {
             swap(nums, i, j);
             i++;
             j--;
         }


    }
    public static void main(String... args)
    {
        int nums[] = new int[]{1, 2, 1};
        nextPermutation(nums);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));

    }
}
