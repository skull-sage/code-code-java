package leetcode.intervals;

import java.util.*;

public class OverlapRemoval
{
    static public int eraseOverlapIntervals(int[][] intervals) {

       // problem definition garuntees
        record Interval(Integer low, Integer high){};

        List<Interval> dataList = new ArrayList<>();
        for(int[] it: intervals)
            dataList.add(new Interval(it[0], it[1]));

        Comparator<Interval> comparator = Comparator.comparing(Interval::low).thenComparing(Interval::high);
        dataList.sort(comparator);

        int toRemove = 0;
        Iterator<Interval> itr = dataList.iterator();
        int high = itr.next().high;

        while(itr.hasNext())
        {
            Interval current = itr.next();
            if(high > current.low) {
                toRemove++;
                if(high > current.high)
                    high = current.high;
            }   else {
                high = current.high;
            }


        }

        return toRemove;

    }
    public static void main(String... args)
    {
            int testData[][] = { {0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}};
            int result = eraseOverlapIntervals(testData);
            System.out.println(result);
    }
}
