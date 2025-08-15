package streams;

import java.util.*;
import java.util.stream.Stream;

public class StreamExample
{
    /**
     *  java streams are a lazy-evaluation pipeline approach with three steps
     *  1. Initialization from a source
     *  2. Intermediate Processing steps
     *  3. Terminal operation that executes the full pipeline
     * */

    public static void create_stream_example()
    {

         Stream<Integer> intStream = Stream.of(10, 20, 30, 40, 50);
         Stream<String> stringStream = Arrays.stream(new String[]{"Hello", "Stream", "one"});

        List<Integer> intList = new ArrayList<>();
        intList.add(50);
        intList.add(70);
        intList.add(80);
        Stream<Integer> streamFromList = intList.stream();
/*
        map directly doesn't support stream; we will need to use
        entrySet().stream() or keySet().stream()
*/

        // useful to be used as a default value stream
        Stream<String> emptyStream = Stream.empty();

        Stream.Builder<String> streamBuilder = Stream.builder();
        Stream<String> builtStream = streamBuilder.add("One")
                                                .add("Two")
                                                .add("Three")
                                                .build()
        ;

        //generating Stream
        Random random = new Random();
        Stream.generate(random::nextInt).limit(10);


        // what is spliterator ?


    }

    public static void main(String... args){

    }
}
