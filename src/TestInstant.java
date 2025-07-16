import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestInstant {

    public static void main(String... args)
    {

        // mashtor expires on 2023-11-25T11:25:20Z
        Instant pavExpire = Instant.parse("2022-02-07T21:01:08.000Z");
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(pavExpire, ZoneId.of("Asia/Dhaka"));
        System.out.println(zonedDateTime.format(DateTimeFormatter.ofPattern("MM dd, yyyy  HH:mm")));
    }
}
