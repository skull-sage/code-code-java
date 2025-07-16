import java.lang.annotation.*;
import java.lang.reflect.AnnotatedType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TaskSchedule
{

    int dayLapse();
    String at();
}




interface ITask
{
    void execute();
}


@TaskSchedule(dayLapse = 3, at="4:00pm")
class CalcUsageRanking implements ITask
{
       public void execute(){
           // a long-running task to compute user's usage rank
       }
}


public class TestAnnotation
{
    public final static void main(String... args)
    {
        ITask task  = new CalcUsageRanking();
        TaskSchedule schedule = task.getClass().getAnnotation(TaskSchedule.class);
        System.out.printf("Execute every %d days at %s", schedule.dayLapse(), schedule.at());

    }
}
