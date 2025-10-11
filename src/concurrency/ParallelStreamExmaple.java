package concurrency;

import java.util.Arrays;

public class ParallelStreamExmaple
{
    public static void main(String... args){
        long[] dataArr = Cutil.genIntList();

        long elapsedSeq = Cutil.milliElapsed(()-> {
            long sum = 0;
            for(long data : dataArr){
                sum += data;
            }
        });

        long elapsedParallel = Cutil.milliElapsed(()->{
            Arrays.stream(dataArr).parallel().sum();
        });

        System.out.printf("Elapsed Seq: %d\n Elapsed Parallel: %d\n", elapsedSeq, elapsedParallel);
    }
}
