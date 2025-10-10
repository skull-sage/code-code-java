package concurrency;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

    private static final int THRESHOLD = 1000;

    static class SumTask extends RecursiveTask<Long> {
        private final long[] dataArr;
        private final int start;
        private final int end;

        public SumTask(long[] arr, int start, int end){
            this.dataArr = arr;
            this.start = start;
            this.end = end;
        }

        private Long calcSum(long[] arr, int start, int end){
            long sum = 0;
            for (int idx=start; idx < end; idx++){
                sum += arr[idx];
            }
            return  sum;
        }

        @Override
        protected Long compute() {
            long diff = end - start;
            if(diff <= THRESHOLD) {
                return calcSum(dataArr, start, end);
            }

            int mid = start + (end - start)/2;
            SumTask left = new SumTask(dataArr, start, mid);
            SumTask rightTask = new SumTask(dataArr, mid+1, end);

            left.fork();// async execute left task
            long rightResult = rightTask.compute();
            return  rightResult + left.join();
        }
    }

    public static void main(String... args){
            int size = 10_1000;
            long[] dataArr = new long[size];

            Random random = new Random();
            for(int idx=0; idx < dataArr.length; idx++){
                dataArr[idx] = random.nextInt();
            }

            SumTask rootTask = new SumTask(dataArr, 0, dataArr.length);

            ForkJoinPool pool = ForkJoinPool.commonPool();
            long result = pool.invoke(rootTask);

    }
}
