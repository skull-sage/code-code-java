package leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenAllPermutation
{
    public static List<Integer> boxedList(int[] arr)
    {
        List<Integer> list = new ArrayList<>();
        for(int item: arr)
            list.add(item);
        return list;
        //return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    private static void swap(int[] nums, int i, int j)
    {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    // will return same array with permutation
    // or null if there is no more possible
    public static int[] permuteNext(int[] nums)
    {
        int j = nums.length - 2;
        while (j >= 0 && nums[j] >= nums[j+1])
            j--;

        if(j == -1)
            return null; // we reached end of all possibility

        int k = nums.length - 1;
        while(nums[k] <= nums[j])
            k--;

        swap(nums, j, k);

        // it is reverse time
        int l = j + 1;
        int m = nums.length - 1;
        while (l < m)
        {
            swap(nums, l, m);
            l++;
            m--;
        }

        return nums;
    }
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        result.add(boxedList(nums));

        while(true)
        {
            nums = permuteNext(nums);
            if(nums == null) break;

            List<Integer> permuted = boxedList(nums);
            result.add(permuted);

            System.out.println("#=>"+ permuted);
        }

        return result;

    }

    public static void main(String... args)
    {
        int nums[] = {1,1,2};
        List<List<Integer>> result = permuteUnique(nums);
        result.forEach(System.out::println);
    }
}
