package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TownJudge
{
    public int findJudge(int n, int[][] trustArr) {
        Map<Integer, Integer> trustMap = new HashMap<>();
        int[] trustCounter = new int[n+1];
        for(int[] trust: trustArr)
        {
            int trusted = trust[1];
            trustCounter[trusted]++;
            trustMap.put(trust[0], trusted);
        }

        int suspectIdx = -1;
        for (int idx = 1; idx <= n; idx++)
        {
            if(trustCounter[idx] == n - 1)
            {
                if(trustMap.containsKey(idx))
                    return -1;

                if(suspectIdx > 0)
                    return -1;

                suspectIdx = idx;
            }
        }

        return suspectIdx;
    }

    public static void main(String... args){}
}
