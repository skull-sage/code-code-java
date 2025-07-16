package leetcode.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class AliceBobMeeting
{

    public int[] buildNearestSmaller(int[] heights)
    {
        Stack<Integer> stack = new Stack<>();
        int[] nearestElm = new int[heights.length];

        for(int idx=0; idx< heights.length; idx++)
        {
            // stack contains index
            while(stack.isEmpty() == false && heights[stack.peek()] >= heights[idx])
                stack.pop();

            if(stack.empty())
                nearestElm[idx] = -1;
            else
                nearestElm[idx] = stack.peek();

            stack.push(idx);
        }

        return nearestElm;
    }

    public static int[] buildNearestLarger(int[] heights)
    {
        Stack<Integer> stack = new Stack<>();
        int[] nearestElm = new int[heights.length];

        for(int idx = heights.length - 1; idx>= 0; idx--)
        {
            // stack contains index
            while(stack.isEmpty() == false && heights[stack.peek()] <= heights[idx])
                stack.pop();

            if(stack.empty())
                nearestElm[idx] = -1;
            else
                nearestElm[idx] = stack.peek();

            stack.push(idx);
        }

        return nearestElm;
    }

    public static int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        for(int idx=0; idx<queries.length; idx++)
        {
            int[] query = queries[idx];
            if(query[0] > query[1])
            {
                int t = query[0];
                query[0] = query[1];
                query[1] = t;
            }
        }

       /* Arrays.sort(queries, (int[] a, int[] b) -> {
            Integer ay = a[1];
            Integer by = b[1];
            return ay.compareTo(by);
        });*/

        int[] solutionArr = new int[queries.length];
        int[] nearestLarger = buildNearestLarger(heights);

        for(int idx=0; idx<queries.length; idx++)
        {
            int solution = -1;
             int[] query = queries[idx];
             int x = query[0];
             int y = query[1];
             int hx = heights[x];
             int hy = heights[y];

             if(x == y || hx < hy)
             {
                 solution = y;
             }
             else
             {
                 // find t: y < t and hx < ht
                 // t <- find(y, h)
                while (true){
                    int t = nearestLarger[y];
                    if(t == -1 || hx < heights[t])
                    {
                        solution = t;
                        break;
                    }
                    else y = t;
                }


             }

             solutionArr[idx] = solution;

        }

        return solutionArr;

    }
    public static void main(String... args)
    {
        int[] heights = {6,4,8,5,2,7};
        int[][] queries = {{0,1}, {0,3}, {2,4}, {3,4}, {2,2}};

        /*int[] nearestLarger = buildNearestLarger(heights);
        for(int idx=0; idx < heights.length; idx++)
        {
            int t = nearestLarger[idx];
            if(t==-1)
                System.out.printf("[%d]=>NONE ", idx);
            else System.out.printf("[%d]=>%d ", idx, heights[t]);
        }*/
        int[] ansList = leftmostBuildingQueries(heights, queries);
        for(int idx=0; idx<queries.length; idx++)
            System.out.printf("%d, ", ansList[idx]);
    }
}
