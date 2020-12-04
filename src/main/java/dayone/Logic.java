package dayone;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Logic {

  public static Optional<List<Long>> calculateEx1(List<Long> input) {
    for (Long a : input) {
      Optional<Long> match = input.stream().filter(b -> !a.equals(b)).filter(b -> a+b==2020).findFirst();
      if(match.isPresent()) {
        return Optional.of(new ArrayList<>(Arrays.asList(a, match.get())));
      }
    }
    return Optional.empty();
  }

  public static Optional<List<Long>> calculateEx2(List<Long> input) {
    for (Long a : input) {
      for (Long b : input) {
        Optional<Long> match = input.stream().filter(c -> !a.equals(c)).filter(c -> !b.equals(c)).filter(c -> a + b + c == 2020).findFirst();
        if (match.isPresent()) {
          return Optional.of(new ArrayList<>(Arrays.asList(a, b, match.get())));
        }
      }
    }
    return Optional.empty();
  }
}
