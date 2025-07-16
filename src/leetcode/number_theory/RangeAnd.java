package leetcode.number_theory;

import java.util.List;

public class RangeAnd
{
    public static int rangeBitwiseAnd(int x, int y) {


        if(x < y)
        {
            int tmp = x;
            x = y;
            y = tmp;
        }

        // assign ax and by as work storage to find diffLsb
        int ax = x;
        int by = y;

        int idx = 1;
        int diffLsb = 0;

        while(true)
        {
            if(ax == 0 && by == 0)
                break;

            else if(ax == 0) // cause that means we have bit parity
                return 0;

            if( (ax % 2 != by % 2) && diffLsb < idx)
                diffLsb = idx;

            idx++;
            ax = ax >> 1;
            by = by >> 1;
        }

        int result = x >> diffLsb;
        result = result << diffLsb;

        return result;
    }

    public static void main(String... args)
    {
        System.out.println("Hamming Distance: "+rangeBitwiseAnd(44, 41));
    }
}
