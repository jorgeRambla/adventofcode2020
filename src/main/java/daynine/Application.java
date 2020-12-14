package daynine;

import java.util.List;

public class Application {
  public static void main(final String... args) {
    long resultEx1 = Logic.calculateEx1(InputOutput.readInputEx1(), 25);
    System.out.println(String.format("The ex1 result is: %s", resultEx1));

    List<Long> resultEx2 = Logic.calculateEx2(InputOutput.readInputEx1(), resultEx1);
    System.out.println(String.format("The ex2 result is: %s", Logic.extractEx2Value(resultEx2)));
  }
}
