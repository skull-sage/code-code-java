package concurrency;

public class ThreadJoin {

    public static void main(String... args) throws InterruptedException {
        Thread.Builder builder  = Thread.ofVirtual().name("Test-Worker", 0);
        Thread t1 = builder.start(()->{
            System.out.println("Thread starting to Work: " + Thread.currentThread().getName());
            Cutil.sleepSec(4);
            System.out.println("Finished : " + Thread.currentThread().getName());

        });

        t1.join(); // main thread wait before going ot instruction at line 16

        Thread t2 = builder.start(()->{
            System.out.println("Thread starting to Work: " + Thread.currentThread().getName());
            Cutil.sleepSec(2);
            System.out.println("Finished : " + Thread.currentThread().getName());
        });

        t2.join(); // main thread wait until it final print in line 24

        System.out.println("I am done! Goodbye");


    }
}
