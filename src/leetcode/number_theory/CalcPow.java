package leetcode.number_theory;

public class CalcPow
{

    /**
     *  pow(a, exp)
     *      = 1 if exp is 0
     *      = 1 / pow(a, |exp|) if exp < 0
     *      = pow(a, exp/2) * pow(a, exp/2) if exp is even
     *      = a * pow(a, (exp-1)/2) * pow(a, (exp -1) /2) if exp is odd
     * */
    public static double calcRecursivePow(double x, int exp)
    {
        if(exp == 0)
            return 1.0;

        if(exp < 0)
            return 1 / calcRecursivePow(x, -exp);

        if(exp % 2 ==0)
        {
            double result = calcRecursivePow(x, exp >>> 2);
            return result * result;
        }
        else
        {
            double result = calcRecursivePow(x, (exp -1) >>> 2);
            return x * result * result;
        }
    }

    public static double calcLinearPow(int a, int exp)
    {

        if(a == 0)
            return 0;

        if(exp < 0)
        {
            exp = -exp;
            a = 1 / a;
        }

        double result = 1;

        while (exp != 0)
        {
            if(exp == 1)
            {
                result = result * a;
                break;
            }

            else if(exp % 2 != 0)
               result = result * a;

            a = a * a;
            exp = exp >>> 2;

        }

        return result;
    }

    public static void main(String... args)
    {

    }
}
