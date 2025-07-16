package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CombinationSum
{
    static class Solution {

        private void findCombination
                (int[] candidates, int cIdx, int target, List<Integer> curCombi, Consumer<List<Integer>> acceptor)
        {
            if(target <= 0)
            {
                if(target == 0)
                    acceptor.accept(new ArrayList<>(curCombi));

                return;
            }

            if(cIdx < candidates.length)
            {
                int val = candidates[cIdx];
                curCombi.add(val);
                findCombination(candidates, cIdx, target - val, curCombi, acceptor);
                curCombi.remove(curCombi.size() - 1);
                // candidates are sorted, if current value reaches >= target, bigger candidate wont
                if(target - val <= 0)
                    return;


                findCombination(candidates, cIdx+1, target, curCombi, acceptor);
            }

        }

        public List<List<Integer>> combinationSum(int[] candidates, int target) {

            Arrays.sort(candidates);
            List<List<Integer>> list = new ArrayList<>();
            findCombination(candidates, 0, target, new ArrayList<>(), list::add);
            return list;

        }
    }
    public static void main(String... args)
    {
        Object result = new Solution().combinationSum(new int[]{2,3,6,7}, 7);
        System.out.println(result);
    }
}
