package daynine;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logic {

  public static long calculateEx1(List<Long> input, int startAt) {
    int i;
    for(i = startAt; i < input.size(); i++) {
      if(!isValid(input, input.get(i), i)) {
        break;
      }
    }
    return input.get(i);
  }

  private static boolean isValid(List<Long> input, long valueDesired, int currentIndex) {
    for (int i = 0; i <= currentIndex; i++) {
      for (int j = 0; j <= currentIndex; j++) {
        if(i != j) {
          if (input.get(i) + input.get(j) == valueDesired) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static List<Long> calculateEx2(List<Long> input, long desiredValue) {
    for (int i = 0; i < input.size() - 1; i++) {
      for (int j = i + 1; j < input.size(); j++) {
        List<Long> sublist = input.subList(i, j);
        if(sublist.stream().reduce(0L, Long::sum) == desiredValue) {
          return sublist;
        }
      }
    }
    return new ArrayList<>();
  }

  public static long extractEx2Value(List<Long> input) {
    return Collections.min(input) + Collections.max(input);
  }
}
