package concurrency;

public class Cutil {
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
