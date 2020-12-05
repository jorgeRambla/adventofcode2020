package dayfive;


import java.util.List;
import java.util.stream.Collectors;

public class Logic {

  public static List<Long> calculateEx1(List<List<InputOutput.Site>> input) {
    return input.stream()
            .map(Logic::getData)
            .collect(Collectors.toList());
  }

  private static long getData(List<InputOutput.Site> input) {
    List<InputOutput.Site> row = input.stream().filter(item -> item.equals(InputOutput.Site.FRONT) || item.equals(InputOutput.Site.BACK)).collect(Collectors.toList());
    List<InputOutput.Site> column = input.stream().filter(item -> item.equals(InputOutput.Site.LEFT) || item.equals(InputOutput.Site.RIGHT)).collect(Collectors.toList());

    long rowValue = getRow(row, 0, 0, 127);
    long colValue = getColumn(column, 0, 0, 7);

    return rowValue * 8 + colValue;
  }

  private static long getRow(List<InputOutput.Site> input, int currentIndex, int from, int to){
    if(currentIndex == input.size()-1) {
      // End of rec.
      if (input.get(currentIndex).equals(InputOutput.Site.FRONT)) {
        return from;
      } else {
        return to;
      }
    }

    double diff = (to - from) / 2.0;
    if (input.get(currentIndex).equals(InputOutput.Site.FRONT)) {
      return getRow(input, currentIndex + 1, from, to - (int) Math.ceil(diff));
    } else {
      return getRow(input, currentIndex + 1, from + (int) Math.ceil(diff), to);
    }
  }

  private static long getColumn(List<InputOutput.Site> input, int currentIndex, int from, int to){
    if(currentIndex == input.size()-1) {
      // End of rec.
      if (input.get(currentIndex).equals(InputOutput.Site.LEFT)) {
        return from;
      } else {
        return to;
      }
    }

    int diff = (to - from) / 2;
    if (input.get(currentIndex).equals(InputOutput.Site.LEFT)) {
      return getColumn(input, currentIndex + 1, from, to - diff - 1);
    } else {
      return getColumn(input, currentIndex + 1, from + diff + 1, to);
    }
  }
}
