package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomIII
{
    static record MeetingBooking(Long start, Long end, int roomIdx)
    {


        @Override
        public String toString() {
            return String.format("%d %d at room %d", start, end, roomIdx);
        }
    }

    public static int mostBooked(int n, int[][] meetings) {

        int[] bookingCounter = new int[n];

        Comparator<MeetingBooking> comparator = Comparator
                .comparing(MeetingBooking::end).thenComparing(MeetingBooking::roomIdx);

        PriorityQueue<MeetingBooking> bookedQueue = new PriorityQueue<>(comparator);
        PriorityQueue<Integer> availableQueue = new PriorityQueue<>();

        Arrays.sort(meetings, Comparator.comparing(m -> m[0]));

        for(int idx=0; idx < n; idx++)
        {
            availableQueue.add(idx);
        }

        for(int idx = 0; idx < meetings.length; idx++)
        {
            int[] meeting = meetings[idx];
            long start = meeting[0];
            long end = meeting [1];
            int roomIdx = n;


            while(bookedQueue.size() > 0 && bookedQueue.peek().end <= start)
            {
                MeetingBooking endedBooking = bookedQueue.poll();;
                availableQueue.add(endedBooking.roomIdx);
            }

            if(availableQueue.size() > 0)
            {
                roomIdx = availableQueue.poll();
            }
            else
            {
                MeetingBooking oldBooking  = bookedQueue.poll();
                end = oldBooking.end + ( end - start );
                start = oldBooking.end;
                roomIdx = oldBooking.roomIdx;
            }

            MeetingBooking newBooking = new MeetingBooking(start, end, roomIdx);
            bookingCounter[roomIdx]++;
            bookedQueue.add(newBooking);
        }

        int result = 0;
        int maxCount = bookingCounter[0]++;

        for(int idx = 1; idx < n; idx++)
        {
            if(maxCount < bookingCounter[idx])
            {
                maxCount = bookingCounter[idx];
                result = idx;
            }
        }

        return result;

    }

    public static void main(String... args)
    {
       // int n = 2;
        //int[][] meetings = {{0, 10}, {1, 5}, {2, 7}, {3, 4}};

        //int n=3;
        //int[][] meetings = {{3, 7}, {12, 19}, {16, 17}, {1, 17}, {5, 6}};
        // int n=4;
        //int[][] meetings = {{18,19},{3, 12},{17, 19},{2,13}, {7, 10}};

        // int n=4;
       //  int[][] meetings = {{19, 20}, {14, 15}, {13, 14}, {11, 20}};

        int n = 2;
        int[][] meetings = {{1,10}, {2,10}, {3,10}, {4,10}, {5,10}, {6,10}, {7,10}};
        System.out.println(mostBooked(n, meetings));
    }
}
