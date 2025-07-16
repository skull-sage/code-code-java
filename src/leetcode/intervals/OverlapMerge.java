package leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OverlapMerge {

    public static int[][] merge(int[][] intervals) {

        //record Interval(int low, int high){};
        List<int[]> resultList = new ArrayList<>();

       // Comparator<int[]> comparator = Comparator.comparing((int[] a) -> a[0]).thenComparing(a -> a[1]);
        // straight comparator seems to be faster
        Arrays.sort(intervals, (int[] a, int[]b) -> {
            int result = Integer.compare(a[0], b[0]);
            if(result == 0)
                result = Integer.compare(a[1], b[1]);
            return result;
        });

        for(int idx=0; idx < intervals.length; idx++)
        {
            int mergeLow = intervals[idx][0];
            int mergeHigh = intervals[idx][1];

            int j = idx+1;
            while(j < intervals.length && mergeHigh >= intervals[j][0])
            {
                if(mergeHigh < intervals[j][1])
                    mergeHigh = intervals[j][1];

                j++;
            }

            idx = j - 1; // last jth element broke the overlap condition
            resultList.add(new int[]{mergeLow, mergeHigh});

        }


        return resultList.toArray(new int[0][]);

    }

    public static final void main(String... args)
    {
        int[][] testData = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = merge(testData);

        for(int[] it: result)
            System.out.printf("(%d, %d), ", it[0], it[1]);
    }
}
