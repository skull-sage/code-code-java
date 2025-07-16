package leetcode.string;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestUncommonSubstringArr
{

    static  class Candidate{
        int prIdx;
        int count;

        Candidate(int prIdx, int count)
        {
            this.prIdx = prIdx;
            this.count = count;
        }
    };

    public static String[] shortestSubstrings(String[] arr) {
        // pIdx is the first parent the substring is extracted from


        Map<String, Candidate> candidateMap = new HashMap<>();

        /**
         *  1. compute substring exclusive to  idx-string
         *  2. Map such substring to idx
         *  3. revisit and reorder to report lexicographically smallest
         * */
        for(int idx=0; idx < arr.length; idx++)
        {
            String str = arr[idx];
            final Integer prIdx = idx;

            for(int j=0 ; j<str.length(); j++)
            {
                for(int k=j; k < str.length(); k++)
                {
                    String substr = str.substring(j, k+1);
                    candidateMap.compute(substr, (key, val) -> {
                        if(val == null)
                            return new Candidate(prIdx, 1);
                        if(val.prIdx != prIdx)
                            val.count++;

                        return val;

                    });
                };
            }
        }

        String[] result = new String[arr.length];
        for(int idx = 0; idx < arr.length; idx++)
            result[idx] = "";

        for(Map.Entry<String, Candidate> entry : candidateMap.entrySet())
        {
            Candidate candidate = entry.getValue();
            String subs = entry.getKey();
            if(candidate.count == 1)
            {
                String rStr = result[candidate.prIdx];
                if(rStr.isEmpty())
                    rStr = subs;
                else  if(subs.length() < rStr.length())
                        rStr = subs;
                else if(subs.length() == rStr.length() && subs.compareTo(rStr) < 0)
                {
                   rStr = subs;
                }

                result[candidate.prIdx] = rStr;

            }
        }

        return result;

    }


    public static void main(String... args)
    {

        String arr[] = {"gfnt","xn","mdz","yfmr","fi","wwncn","hkdy"};
        String[] result = shortestSubstrings(arr);

        for(int idx=0; idx < arr.length; idx++)
            System.out.printf("[%d]:%s ", idx, result[idx]);
    }
}
