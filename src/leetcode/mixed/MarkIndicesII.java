package leetcode.mixed;

import java.util.Set;

public class MarkIndicesII
{
    public static int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int atSec = 0;
        int totalSec = changeIndices.length;
        int totalMarking = nums.length;

        /**
         *  totalAvailableSecs = totalSec - totalMarking
         *  set to zero all the index that are in changeIndex with sec sequence
         *  index that are not in changeIndices must be decrement by 1 to reach zero
         *  earliest second is when all indices can be made to zero
         * */

        int[] indexZro;
        Set<Integer> decreseSet;


        return atSec;
    }
    public static void main(String... args)
    {
        int[] changeIndices = {1,2,1,2,1,2,1,2};
        int[] nums = {0, 0, 1, 2};
    }
}
