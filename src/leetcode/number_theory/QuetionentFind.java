package leetcode.number_theory;

public class QuetionentFind
{
    public static int divide(int dividend, int divisor) {
        // number of times we add an integer results in multiplication
        // number of times we can subtract divisor is the quotient

        // Handling an special edge case: to pass wrong answer :/
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int result = 0;
        long dividendL = dividend, divisorL = divisor;

        boolean toNegate = (dividend < 0) ^ (divisor < 0);
        dividendL = Math.abs(dividend);
        divisorL = Math.abs(divisor);

        // going from MSB to LSB
        for(int idx=31; idx >= 0; idx--)
        {
            long divisorShift = divisorL << idx;

            if(dividendL - divisorShift >= 0)
            {
                // be aware of operator precedence between + and <<
                result = result + (1 << idx);
                dividendL = dividendL - divisorShift;
            }
        }

        return  toNegate ? -result : result;
    }

    public static void main(String... args){
        int result = divide(18, 1);
        System.out.println(result);

    }
}
