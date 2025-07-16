package leetcode.number_theory;

import java.util.ArrayList;
import java.util.List;

public class KthFactor
{
    public static int kthFactor(int n, int k) {

        List<Integer> lowerList = new ArrayList<>();
        List<Integer> upperList = new ArrayList<>();
        int t = 1;
        while(t*t <= n)
        {
             if(n % t == 0)
            {
                lowerList.add(t);
                int q = n / t;
                if(q != t)
                    upperList.add(n/t);
            }

            if(k == lowerList.size())
                return t;

            t++;

        }

        int k1 = k - lowerList.size();
        if(upperList.size() < k1)
            return -1;

        int idx = upperList.size() - k1;

        return upperList.get(idx);

    }
    public static void main(String... args)
    {
        int n = 4, k = 4;
        Object result = kthFactor(n, k);
        System.out.println(result);
    }
}
