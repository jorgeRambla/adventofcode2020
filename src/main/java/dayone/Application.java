package dayone;

import java.util.List;
import java.util.Optional;

public class Application {
  public static void main(final String... args) {
    Optional<List<Long>> result = Logic.calculateEx1(InputOutput.readInputEx1());
    if(result.isPresent()) {
      Long finalResult = result.get().get(0) * result.get().get(1);
      System.out.println(String.format("The matched values are: %s, and the result is: %s", result.get(), finalResult));
    } else {
      System.out.println("The provided data does not contains a valid solution for Ex1");
    }

    Optional<List<Long>> resultEx2 = Logic.calculateEx2(InputOutput.readInputEx1());
    if(resultEx2.isPresent()) {
      Long finalResult = resultEx2.get().get(0) * resultEx2.get().get(1) * resultEx2.get().get(2);
      System.out.println(String.format("The matched values are: %s, and the result is: %s", resultEx2.get(), finalResult));
    } else {
      System.out.println("The provided data does not contains a valid solution for Ex2");
    }
  }
}
