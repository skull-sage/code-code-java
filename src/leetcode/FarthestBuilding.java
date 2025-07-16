package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FarthestBuilding {

    static class Solution
    {


        public int furthestBuilding(int[] heights, int bricks, int ladders) {

            int[] bricksNeeded = new int[heights.length];

            if(heights.length == ladders)
                return heights.length - 1;

            bricksNeeded[0] = 0;
            for(int idx= 1; idx < heights.length; idx++)
            {
                if(heights[idx] > heights[idx-1])
                {
                    bricksNeeded[idx] = heights[idx] - heights[idx-1];
                }
                else bricksNeeded[idx] = 0;
            }

            PriorityQueue<Integer> queue = new PriorityQueue<>();

            int included = -1, totalBricked = 0;
            for(int idx=0; idx < bricksNeeded.length; idx++)
            {
                int needed = bricksNeeded[idx];

                if(needed == 0)
                {
                    included++;
                }
                else if(queue.size() < ladders)
                {
                    queue.add(needed);
                    included++;
                }
                else
                {
                    queue.add(needed);
                    int leastNeeded = queue.poll();
                    if(totalBricked + leastNeeded <= bricks)
                    {
                        included++;
                        totalBricked += leastNeeded;
                    }
                    else break; // ****
                }


            }

            return included;
        }
    }

    public static void main(String... args)
    {

        int[] heights = {2,5, 3, 5, 4, 5};
        int bricks = 0;
        int ladders = 3;

        int result = new Solution().furthestBuilding(heights, bricks, ladders);
        System.out.println(result);
        if(true) return;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(50);
        queue.offer(40);
        queue.add(14);
        System.out.println(queue.poll());
        System.out.println(queue);
    }




}
