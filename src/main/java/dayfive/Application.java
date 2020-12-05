package dayfive;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Application {
  public static void main(final String... args) {
    List<Long> ex1 = Logic.calculateEx1(InputOutput.readInputEx1());
    System.out.println(ex1);
    System.out.println(String.format("The highest seat id is: %s", Collections.max(ex1)));

    long min = Collections.min(ex1);
    long max = Collections.max(ex1);

    List<Long> allSeats = LongStream.range(min, max).boxed().collect(Collectors.toList());
    Optional<Long> mySeat = allSeats.stream().filter(seatId -> !ex1.contains(seatId)).findAny();
    if (mySeat.isPresent()) {
      System.out.println(String.format("My seat seat id is: %s", mySeat.get()));
    } else {
      System.err.println("I miss in my life of service :(");
    }
  }
}
