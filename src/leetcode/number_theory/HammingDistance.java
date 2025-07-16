package leetcode.number_theory;

public class HammingDistance
{
    public static int hammingDistance(int x, int y) {

        int diff = 0;

        while(x > 0 || y > 0)
        {
            if((x % 2) != (y % 2))
                diff++;

            x = x >> 1;
            y = y >> 1;


        }

        return diff;
    }

    public static void main(String... args)
    {
        System.out.println("Hamming Distance: "+hammingDistance(1, 4));
    }
}
