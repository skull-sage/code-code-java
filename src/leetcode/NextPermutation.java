package leetcode;

public class NextPermutation
{
    static class Solution {
        public void nextPermutation(int[] nums) {

            int lastElm = nums[nums.length - 1];


            boolean isDecending = true;
            for(int idx=0; idx < nums.length - 1; idx++)
            {
                if(nums[idx] >= nums[idx+1] == false){
                    isDecending = false;
                    break;
                }
            }

            if(isDecending){ // lets mirror it
                int start = 0, end = nums.length - 1;
                while(start < end)
                {
                    int temp = nums[end];
                    nums[end] = nums[start];
                    nums[start] = temp;
                    start++;
                    end--;
                }

                return;
            }


            for(int idx = nums.length - 1;  idx >= 0; idx-- )
            {
                for(int j = idx - 1; j>=0 ; j--)
                {
                    if(nums[j] < nums[idx]){

                        int tmp = nums[idx];
                        nums[idx] = nums[j];
                        nums[j] = tmp;

                    }
                }

            }



        }
    }

    public static void main(String... args)
    {
        int[] arr = {1, 1, 5};
        new Solution().nextPermutation(arr);

        for(int elm : arr)
            System.out.printf("%d ", elm);
        System.out.println("Finished");
    }
}
