package concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Cutil {

    public static long nanoElapsed(Runnable task){
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }

    public static long milliElapsed(Runnable task){
        return TimeUnit.NANOSECONDS.toMillis(nanoElapsed(task));
    }

    public static long secElapsed(Runnable task){
        return TimeUnit.NANOSECONDS.toSeconds(nanoElapsed(task));
    }


    public static long[] genIntList(){
        int size = 10_1000;
        long[] dataArr = new long[size];

        Random random = new Random();
        for(int idx=0; idx < dataArr.length; idx++){
            dataArr[idx] = random.nextInt();
        }

        return  dataArr;
    }

    public static void sleepMillis(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sleepSec(long secs){
        try {
            Thread.sleep(secs*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
