package leetcode.string;

import java.util.*;

public class GroupAnagrams
{
    // lightweight version of counting sort
    public static String sortString(String str)
    {
        // counting sort based approach

        int k = 26;
        int[] C = new int[k];
        int[] B = new int[str.length()];
        int[] A = new int[str.length()];

        for(int idx=0; idx < str.length(); idx++)
            A[idx] = str.charAt(idx) - 'a';

        for(int j=0; j<A.length; j++)
            C[A[j]] = C[A[j]]+1;

        for(int i=1; i<k; i++)
            C[i] = C[i] + C[i-1];

        for(int j = 0; j < A.length; j++)
        {
            int t = C[A[j]];
            B[t-1] = A[j];
            C[A[j]]--;
        }

        StringBuilder builder = new StringBuilder();
        for(int idx=0; idx < B.length; idx++)
        {
            char ch = (char)(B[idx] + 'a');
            builder.append(ch);
        }

        return builder.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramMap = new HashMap<>();

        /*String[] sortedArr = new String[strs.length];
        for(int idx=0; idx< strs.length; idx++)
            sortedArr[idx] = sortString(strs[idx]);
*/
        for(String s: strs)
        {
            String sorted = sortString(s);

            if(anagramMap.containsKey(sorted))
            {
                List<String> gramList = anagramMap.get(sorted);
                gramList.add(s);
            }
            else
            {
                List<String> gramList = new ArrayList<>();
                gramList.add(s);
                anagramMap.put(sorted, gramList);
            }
        }

        return  anagramMap.values().stream().toList();


    }

    public static void main(String... args)
    {

        System.out.println(sortString("tankbank"));

    }
}
