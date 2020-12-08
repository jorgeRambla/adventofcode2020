package dayseven;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Application {
  public static void main(final String... args) {
    long result = Logic.count(InputOutput.readInput(), "shinygold");
    System.out.println(String.format("There is %s for shiny gold bags", result));


    result = Logic.countEx2(InputOutput.readInput(), "shinygold");
    System.out.println(String.format("There is %s for shiny gold bags in ex2", result));

  }
}
