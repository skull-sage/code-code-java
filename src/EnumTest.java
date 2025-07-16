import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

interface MessageFlag{
    String DEBUG = "DEBUG";
    String INFO = "INFO";
    String WARNING = "WARNING";
    String ERROR = "ERROR";

}

public class EnumTest
{

    public static void concurrentModTest(Map objMap, Object key1, Object key2){

        Thread itrThread = new Thread(()->{
            // AsyncUtil.uncheckedSleep(700);
            for(Object key: objMap.keySet())
            {
                AsyncUtil.uncheckedSleep(2000);
                System.out.printf("ITR-THREAD: (%s => %s)\n", key, objMap.get(key));

            };
        });

        itrThread.start();

        AsyncUtil.uncheckedSleep(300);
        System.out.printf("removing value for %s => %s\n", key1, (objMap.remove(key1)));
        AsyncUtil.uncheckedSleep(500);
        System.out.printf("removing value for %s => %s\n", key2, (objMap.remove(key2)));
    }

    public static void main(String... args)
    {
        enum MessageFlag{
            DEBUG, INFO, WARNING, ERROR
        }

        EnumMap<MessageFlag, String> colorMap = new EnumMap<>(MessageFlag.class);
        colorMap.put(MessageFlag.INFO, "blue");
        colorMap.put(MessageFlag.WARNING, "amber");
        colorMap.put(MessageFlag.ERROR, "red");
        colorMap.put(MessageFlag.DEBUG, "grey");

        Object[] keyArr =  colorMap.keySet().toArray();
        System.out.println(MessageFlag.WARNING.ordinal());
        System.out.println(keyArr[2]); // prints warning

        Thread itrThread = new Thread(()->{
           // AsyncUtil.uncheckedSleep(700);
            colorMap.keySet().forEach(key -> {
                AsyncUtil.uncheckedSleep(2000);
                System.out.printf("ITR-THREAD: (%s => %s)\n", key, colorMap.remove(key));
            });
        });

        itrThread.start();

        AsyncUtil.uncheckedSleep(100);
        colorMap.remove(MessageFlag.DEBUG); // removing value for DEBUG
        AsyncUtil.uncheckedSleep(200);
        colorMap.remove(MessageFlag.WARNING); // removing value for WARNING

        /*concurrentModTest(colorMap, MessageFlag.DEBUG, MessageFlag.INFO);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("Naruto", "Ninja");
        hashMap.put("Luffy", "Rubber Man");
        hashMap.put("Code Geas", "Goddu");
        hashMap.put("Mask", "Joker");
        hashMap.put("Kakashi", "Ninja");

        concurrentModTest(hashMap, "Naruto", "Kakashi");*/


    }
}
