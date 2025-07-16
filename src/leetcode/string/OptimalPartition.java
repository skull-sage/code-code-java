package leetcode.string;

import java.util.HashSet;
import java.util.Set;

public class OptimalPartition
{
    public static int partitionString(String s) {

        int subsCount = 0;
        Set<Character> chSet = new HashSet<>();

        for(char ch : s.toCharArray())
        {
            if(chSet.contains(ch))
            {
                chSet = new HashSet<>();
                subsCount++;
            }
            chSet.add(ch);

        }

        return subsCount+1;
    }

    public static void main(String... args)
    {
        //System.out.println(partitionString("aaaaaaa"));
        System.out.println(3 << 2);
    }
}
