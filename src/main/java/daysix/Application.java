package daysix;

import java.util.Set;

public class Application {
  public static void main(final String... args) {
    long result = InputOutput.readInputEx1().stream().map(Set::size).reduce(0, Integer::sum);
    System.out.println(String.format("The sum of distinct groups in ex1 is: %s", result));

    result = InputOutput.readInputEx2().stream().map(Set::size).reduce(0, Integer::sum);
    System.out.println(String.format("The sum of distinct groups in ex2 is: %s", result));
  }
}
