package leetcode;

import java.util.List;
import java.util.Map;

class Solution {
    public String longestPalindrome(String s) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        char[] inputChars = ("0123456789" + alphabets + alphabets.toUpperCase()).toCharArray();

      //  Map<String, List>

        throw new UnsupportedOperationException();
    }
}

public class TestPalindrome
{
    public int[][] emptyReturn()
    {
        return new int[][]{};
    }

    public static void main(String... args){
     //   int[] testArr = new int[]{1,12,1,2,5,50,3};
      //  long result = new leetcode.Solution().largestPerimeter(testArr);
      //  System.out.println(result);

        String result = new Solution().longestPalindrome("asljlj");
        System.out.println(result);

    }
}
