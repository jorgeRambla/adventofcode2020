package dayseven;


import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Logic {

  static long count(Map<String, List<String>> input, final String color) {
    return input.entrySet().stream()
            .filter(entry ->
                    entry.getValue().stream()
                            .anyMatch(value -> value.matches("\\d".concat(color)) || checkColor(value, input, "\\d".concat(color))))
            .count();
  }

  static boolean checkColor(final String checkedColor, final Map<String, List<String>> input, final String regex) {
    return input.containsKey(checkedColor.replaceFirst("\\d", StringUtils.EMPTY)) &&
            input.get(checkedColor.replaceFirst("\\d", StringUtils.EMPTY)).stream()
                    .anyMatch(value -> value.matches(regex) || checkColor(value, input, regex));
  }

  static long countEx2(Map<String, List<String>> input, final String color) {
    long bagCount = 0;
    for (String value : input.get(color)) {
      String number = value.replaceAll("[^\\d]", StringUtils.EMPTY);
      String formattedColor = value.replaceAll("\\d", StringUtils.EMPTY);
      long count = Long.parseLong(number);
      bagCount += count + count * countEx2(input, formattedColor);
    }
    return bagCount;
  }
}
