import java.io.UncheckedIOException;

public final class AsyncUtil
{
    public static void uncheckedSleep(long millis){
        try{
        Thread.sleep(millis);
        }catch (InterruptedException exception){
            throw new RuntimeException(exception);
        }
    }
}
