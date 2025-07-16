package dsa;

public class CountingSort
{
    public static void main(String... args)
    {

        int k = 26;
        int[] A = {3, 3, 3, 1, 1, 1, 1, 2, 2};
        int[] C = new int[k];
        int[] B = new int[A.length];
        for(int j=0; j<A.length; j++)
            C[A[j]] = C[A[j]]+1;

        for(int i=1; i<k; i++)
            C[i] = C[i] + C[i-1];

        for(int idx=0; idx < C.length; idx++)
            System.out.printf("%d ", C[idx]);

        System.out.println("## OUTPUT ");

        for(int j = 0; j < A.length; j++)
        {
            int t = C[A[j]];
            B[t-1] = A[j];
            C[A[j]]--;
        }

        for(int idx=0; idx < B.length; idx++)
            System.out.printf("%d ", B[idx]);
    }
}
