package leetcode.arrays;

public class KthPermutation
{
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
        int smIdx = nums.length - 2;
        while (smIdx >= 0 && nums[smIdx] >= nums[smIdx+1])
            smIdx--;

        if(smIdx == -1)
            return null; // we reached end of all possibility

        int gtSmIdx = nums.length - 1;
        while(nums[gtSmIdx] <= nums[smIdx])
            gtSmIdx--;

        swap(nums, smIdx, gtSmIdx);

        // it is reverse time
        int l = smIdx + 1;
        int m = nums.length - 1;
        while (l < m)
        {
            swap(nums, l, m);
            l++;
            m--;
        }

        return nums;
    }

    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for(int idx=0; idx < n; idx++)
        {
            nums[idx] = idx+1;
        }

        for(int p=1; p < k; p++)
        {
            permuteNext(nums);
        }

        StringBuilder builder = new StringBuilder();
        for(int a:nums)
            builder.append(String.valueOf(a));

        return builder.toString();

    }

}
